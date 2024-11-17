<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use Illuminate\Database\Eloquent\Model;
use App\Models\RecoverAccount;
use Illuminate\Support\Facades\Request;
use Illuminate\Validation\Rule;
use MoonShine\ActionButtons\ActionButton;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Fields\Number;
use MoonShine\Fields\Relationships\BelongsTo;
use MoonShine\Fields\Switcher;
use MoonShine\Fields\Text;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;
use MoonShine\Pages\Crud\DetailPage;
use MoonShine\Pages\Crud\FormPage;
use MoonShine\Pages\Crud\IndexPage;

/**
 * @extends ModelResource<RecoverAccount>
 */
#[Icon('heroicons.user-group')]
class RecoverAccountResource extends ModelResource
{
    protected string $model = RecoverAccount::class;

    protected string $title = 'Recover accounts';

    protected bool $createInModal = false;

    protected bool $editInModal = true;

    protected bool $detailInModal = false;

    protected bool $errorsAbove = true;


    public function import(): ?ImportHandler
    {
        return null;
    }

    public function export(): ?ExportHandler
    {
        return null;
    }

    public function getActiveActions(): array
    {
        return ['view', 'delete', 'massDelete'];
    }

    public function title(): string
    {
        return __('moonshine::ui.resource.recover_account_title');
    }

    public function redirectAfterSave(): string
    {
        $refer = Request::header('referer');
        return $refer ?? '/';
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function filters(): array
    {
        return [
            BelongsTo::make(__('moonshine::ui.resource.recover_account.account_name'),'accounts', 'username', new AccountResource())
                ->searchable(),
            Text::make(static fn() => __('moonshine::ui.resource.recover_account.email'), 'email'),
        ];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                ID::make()->sortable(),
                BelongsTo::make(__('moonshine::ui.resource.recover_account.account_name'),'accounts', 'username', new AccountResource())
                ->searchable(),
                Text::make(__('moonshine::ui.resource.recover_account.email'), 'email'),
                Number::make(__('moonshine::ui.resource.recover_account.code'),'code')
                    ->min(10000)
                    ->max(99999)
                    ->default(random_int(10000,99999))
                    ->buttons()
                    ->eye(),
                Text::make(__('moonshine::ui.resource.recover_account.token'),'token'),
                Switcher::make(__('moonshine::ui.resource.recover_account.active'),'active')->default(true),
            ]),
        ];
    }

    /**
     * @return list<Field>
     */
    public function indexFields(): array
    {
        return [
            ID::make()->sortable(),
            BelongsTo::make(__('moonshine::ui.resource.recover_account.account_name'),'accounts', 'username', new AccountResource())
            ->searchable(),
            Text::make(__('moonshine::ui.resource.recover_account.email'), 'email'),
            Switcher::make(__('moonshine::ui.resource.recover_account.active'),'active')->default(true),
        ];
    }

    /**
     * @return list<Field>
     */
    public function detailFields(): array
    {
        return [
            ID::make()->sortable(),
            BelongsTo::make(__('moonshine::ui.resource.recover_account.account_name'),'accounts', 'username', new AccountResource())
            ->searchable(),
            Text::make(__('moonshine::ui.resource.recover_account.email'), 'email'),
            Switcher::make(__('moonshine::ui.resource.recover_account.active'),'active')->default(true),
        ];
    }

    /**
     * @param RecoverAccount $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [
            'account_id' => ['required', 'exists:accounts,id', Rule::unique('recover_accounts', 'account_id')],
            'email' => ['required','email', 'exists:accounts,email', Rule::unique('recover_accounts', 'email')],
            'code' => ['required', 'integer', 'between:10000,99999'],
            'token' => ['required', 'string'],
        ];
    }

     /**
     * @param RecoverAccount $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#validation-error-response-format
     */
    public function validationMessages(): array
    {
        return [
            'account_id.unique' => 'La cuenta ya tiene un código para recuperar cuenta.',
            'account_id.exists' => 'La cuenta no existe en el sistema.',
            'email.email' => 'El campo correo electrónico es inválido.',
            'email.unique' => 'Elimine el correo electrónico de la tabla, para recuperar cuenta nuevamente.',
            'email.required' => 'El campo correo electrónico es obligatorio.',
            'email.exists' => 'El correo electrónico no existe en el sistema.',
            'code.required' => 'El campo código es obligatorio.',
            'code.between' => 'El campo código debe estar entre 10000 y 99999.',
            'token.required' => 'El campo token es obligatorio.',
        ];
    }

}
