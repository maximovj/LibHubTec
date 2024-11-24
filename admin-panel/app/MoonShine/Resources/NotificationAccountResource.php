<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use App\Enums\NotificationAccountStatus;
use MoonShine\Fields\Image;
use Illuminate\Contracts\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use App\Models\NotificationAccount;
use Illuminate\Support\Facades\Request;
use MoonShine\ActionButtons\ActionButton;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Enums\JsEvent;
use MoonShine\Fields\Checkbox;
use MoonShine\Fields\Enum;
use MoonShine\Fields\File;
use MoonShine\Fields\Json;
use MoonShine\Fields\Markdown;
use MoonShine\Fields\Relationships\BelongsTo;
use MoonShine\Fields\Switcher;
use MoonShine\Fields\Text;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;
use MoonShine\Models\MoonshineUser;
use MoonShine\MoonShineUI;
use MoonShine\Notifications\MoonShineNotification;
use MoonShine\Resources\MoonShineUserResource;
use MoonShine\Support\AlpineJs;

/**
 * @extends ModelResource<NotificationAccount>
 */
#[Icon('heroicons.star')]
class NotificationAccountResource extends ModelResource
{
    protected string $model = NotificationAccount::class;

    protected string $title = 'NotificationAccount'; // Section title

    protected string $column = 'id';

    protected bool $createInModal = false;

    protected bool $editInModal = true;

    protected bool $detailInModal = false;

    protected bool $isAsync = true;

    protected function onBoot(): void
    {
        //MoonShineUI::toast('Página cargada', 'success');
    }

    public function import(): ?ImportHandler
    {
        return null;
    }

    public function export(): ?ExportHandler
    {
        return null;
    }

    public function redirectAfterSave(): string
    {
        $referer = Request::header('referer');
        return $referer ?? '/';
    }

    public function getActiveActions(): array
    {
        return ['create', 'view', 'delete'];
    }

    public function title(): string
    {
        return __('moonshine::ui.resource.notification_account_title');
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                ID::make()->sortable(),
                BelongsTo::make(
                    static fn() => __('moonshine::ui.resource.notification_account.user'),
                    'user',
                    fn($item) => "$item->name | $item->email",
                    resource: new MoonShineUserResource())
                    ->valuesQuery(fn(Builder $query, Field $field) => $query->where('id', auth()->id()))
                    ->withImage('avatar', 'public', 'moonshine_users')
                    ->default(MoonshineUser::find(auth()->id()))
                    ->disabled(),
                BelongsTo::make(
                    static fn() => __('moonshine::ui.resource.notification_account.account'),
                    'account',
                    fn($item) => "$item->username | $item->email",
                    resource: new AccountResource())
                    ->searchable(),
                Text::make(static fn() => __('moonshine::ui.resource.notification_account.subject'), 'subject')
                    ->required()
                    ->placeholder('Subject'),
                Markdown::make(static fn() => __('moonshine::ui.resource.notification_account.content'), 'content')
                    ->required(),
                File::make(static fn() => __('moonshine::ui.resource.notification_account.attach'),'attach'),
                Image::make(static fn() => __('moonshine::ui.resource.notification_account.signature'),'signature'),
                Enum::make(static fn() => __('moonshine::ui.resource.notification_account.status'), 'status')
                    ->attach(NotificationAccountStatus::class)
                    ->disabled()
                    ->onApply(function(Model $item){
                        $item->status = NotificationAccountStatus::Send;
                        return $item;
                    }),
                Switcher::make(static fn() => __('moonshine::ui.resource.notification_account.send_email'), 'send_email'),
                Json::make(static fn() => __('moonshine::ui.resource.notification_account.tags'), 'tags')
                    ->vertical()
                    ->onlyValue()
                    ->creatable(limit: 6)
                    ->removable(),
            ]),
        ];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function indexFields(): array
    {
        return [
            ID::make()->sortable(),
            BelongsTo::make(
                static fn() => __('moonshine::ui.resource.notification_account.user'),
                'user',
                fn($item) => "$item->name | $item->email",
                resource: new MoonShineUserResource())
                ->valuesQuery(fn(Builder $query, Field $field) => $query->where('id', auth()->id()))
                ->withImage('avatar', 'public', 'moonshine_users')
                ->default(MoonshineUser::find(auth()->id()))
                ->disabled(),
            BelongsTo::make(
                static fn() => __('moonshine::ui.resource.notification_account.account'),
                'account',
                fn($item) => "$item->username | $item->email",
                resource: new AccountResource())
                ->searchable(),
            Text::make(static fn() => __('moonshine::ui.resource.notification_account.subject'), 'subject')
                ->required()
                ->placeholder('Subject'),
            Enum::make(static fn() => __('moonshine::ui.resource.notification_account.status'), 'status')
                ->attach(NotificationAccountStatus::class)
                ->onApply(function(Model $item){
                    $item->status = NotificationAccountStatus::Send;
                    return $item;
                }),
            Switcher::make(static fn() => __('moonshine::ui.resource.notification_account.send_email'), 'send_email'),
        ];
    }

    /**
     * @param NotificationAccount $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [
            'subject' => ['required', 'string', 'min:4', 'max:160'],
            'content' => ['required', 'string', 'min:4'],
        ];
    }

    public function validationMessages(): array
    {
        return [
            'subject.required' => 'El campo asunto es obligatorio.',
            'content.required' => 'El campo contenido es obligatorio.',
            'subject.min' => 'El campo asunto requiere mínimo 4 caracteres',
            'content.min' => 'El campo contenido requiere mínimo 4 caracteres.',
        ];
    }
}
