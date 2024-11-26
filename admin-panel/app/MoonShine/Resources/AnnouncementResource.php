<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use MoonShine\Fields\Image;
use Illuminate\Database\Eloquent\Model;
use App\Models\Announcement;
use Illuminate\Database\Eloquent\Builder;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Fields\Fields;
use MoonShine\Fields\Json;
use MoonShine\Fields\Markdown;
use MoonShine\Fields\Relationships\BelongsTo;
use MoonShine\Fields\Relationships\ModelRelationField;
use MoonShine\Fields\Switcher;
use MoonShine\Fields\Text;
use MoonShine\Fields\Url;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;
use MoonShine\Models\MoonshineUser;
use MoonShine\Resources\MoonShineUserResource;

/**
 * @extends ModelResource<Announcement>
 */
#[Icon('heroicons.star')]
class AnnouncementResource extends ModelResource
{
    protected string $model = Announcement::class;

    protected string $title = 'Announcements';

    protected string $column = 'id';

    protected bool $createInModal = false;

    protected bool $editInModal = false;

    protected bool $detailInModal = false;

    protected bool $isAsync = true;

    public function title(): string
    {
        return __('moonshine::ui.resource.announcement_title');
    }

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

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                ID::make()->sortable(),
                BelongsTo::make(
                    fn()=> __('moonshine::ui.resource.announcement.user'),
                    'moonshine_user',
                    fn(Model $item) => "$item->name | $item->email",
                    new MoonShineUserResource())
                    ->valuesQuery(fn(Builder $query, Field $field) =>
                        $query->where('id', auth()->id()))
                    ->withImage('avatar', 'public', 'moonshine_users')
                    ->default(MoonshineUser::find(auth()->id()))
                    ->disabled()
                    ->required(),
                Text::make(
                    fn()=> __('moonshine::ui.resource.announcement.title'),
                    'title')
                    ->placeholder(__('moonshine::ui.resource.announcement.title'))
                    ->required(),
                Markdown::make(
                    fn()=> __('moonshine::ui.resource.announcement.content'),
                    'content')
                    ->required(),
                Url::make(
                    fn()=> __('moonshine::ui.resource.announcement.link'),
                    'link')
                    ->title(fn(string $url, Url $ctx) => str($url)->limit(3))
                    ->placeholder(__('moonshine::ui.resource.announcement.link'))
                    ->blank(),
                Json::make(
                    fn()=> __('moonshine::ui.resource.announcement.pictures'),
                    'pictures')
                    ->hint('Insert pictures with a 6 limit')
                    ->vertical()
                    ->onlyValue('picture', Image::make('picture'))
                    ->creatable(limit: 6)
                    ->removable(),
                Text::make(
                    fn()=> __('moonshine::ui.resource.announcement.tags'),
                    'tags')->tags(),
                Switcher::make(
                    fn()=> __('moonshine::ui.resource.announcement.deleted'),
                    'deleted'),
            ]),
        ];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function indexFields(): array
    {
        return [
            BelongsTo::make(
                fn()=> __('moonshine::ui.resource.announcement.user'),
                'moonshine_user',
                fn(Model $item) => "$item->name | $item->email",
                new MoonShineUserResource())
                ->valuesQuery(fn(Builder $query, Field $field) =>
                    $query->where('id', auth()->id()))
                ->withImage('avatar', 'public', 'moonshine_users')
                ->default(MoonshineUser::find(auth()->id()))
                ->disabled()
                ->required(),
            Text::make(
                fn()=> __('moonshine::ui.resource.announcement.title'),
                'title')
                ->placeholder(__('moonshine::ui.resource.announcement.title'))
                ->required(),
            Text::make(
                fn()=> __('moonshine::ui.resource.announcement.tags'),
                'tags')->tags(),
            Switcher::make(
                fn()=> __('moonshine::ui.resource.announcement.deleted'),
                'deleted'),
        ];
    }

    /**
     * @param Announcement $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [
            'moonshine_user_id' => ['exists:moonshine_users,id', 'nullable'],
            'title' => ['required', 'string', 'min:4', 'max:160'],
            'content' => ['required', 'string', 'min:4'],
            'link' => ['sometimes', 'url', 'nullable'],
        ];
    }

    protected function beforeDeleting(Model $item): Model
    {
        $item->deleted = true;
        $item->save();
        return $item;
    }

}
