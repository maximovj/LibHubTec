<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use App\Models\Account;
use MoonShine\Fields\ID;

use MoonShine\Fields\Field;
use MoonShine\Attributes\Icon;
use MoonShine\Decorations\Block;
use MoonShine\Resources\ModelResource;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Request;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Enums\PageType;
use MoonShine\Fields\Image;
use MoonShine\Fields\Number;
use MoonShine\Fields\Password;
use MoonShine\Fields\PasswordRepeat;
use MoonShine\Fields\Select;
use MoonShine\Fields\Text;
use MoonShine\Fields\Textarea;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;

/**
 * @extends ModelResource<Account>
 */
#[Icon('heroicons.users')]
class AccountResource extends ModelResource
{
    protected string $model = Account::class;

    protected ?PageType $redirectAfterSave = PageType::INDEX;

    protected string $title = 'Accounts';

    public string $column = 'username';

    protected bool $createInModal = true;

    protected bool $editInModal = true;

    protected bool $detailInModal = false;

    protected bool $errorsAbove = true;

    public function title(): string
    {
        return __('moonshine::ui.resource.account_title');
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

    public function filters(): array
    {
        return [
            Text::make(static fn() => __('moonshine::ui.resource.account.name'), 'name'),
            Text::make(static fn() => __('moonshine::ui.resource.account.last_name'), 'last_name'),
            Text::make(static fn() => __('moonshine::ui.resource.account.username'), 'username'),
            Text::make(static fn() => __('moonshine::ui.resource.account.email'), 'email'),
        ];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function formFields(): array
    {
        return [
            Block::make([
                ID::make()->sortable(),
                Text::make(
                    static fn() => __('moonshine::ui.resource.account.name'),
                    'name')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.name')),
                Text::make(
                    static fn() => __('moonshine::ui.resource.account.last_name'),
                    'last_name')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.last_name')),
                Text::make(
                    static fn() => __('moonshine::ui.resource.account.control_number'),
                    'control_number')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.control_number')),
                Select::make(static fn() => __('moonshine::ui.resource.account.sex'), 'sex')
                    ->options([
                        'mujer' => 'Mujer',
                        'hombre' => 'Hombre',
                        'binario' => 'Binario',
                        'otro' => 'Otro',
                    ])
                    ->default('otro')
                    ->required(),
                Number::make(static fn() => __('moonshine::ui.resource.account.age'), 'age')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.age'))
                    ->buttons()
                    ->min(1)
                    ->max(190)
                    ->default(18),
                Number::make(static fn() => __('moonshine::ui.resource.account.grade'), 'grade')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.grade'))
                    ->buttons()
                    ->min(1)
                    ->max(20)
                    ->default(1),
                Select::make(static fn() => __('moonshine::ui.resource.account.shift'), 'shift')
                    ->required()
                    ->options([
                        'matutino' => 'Matutino',
                        'vespertino' => 'Vespertino',
                    ])
                    ->default('matutino'),
                Image::make(static fn() => __('moonshine::ui.resource.account.photo'), 'photo')
                    ->disk(config('moonshine.disk', 'public'))
                    ->dir('accounts')
                    ->allowedExtensions(['jpg', 'png', 'jpeg'])
                    ->nullable(),
                Textarea::make(
                    static fn() => __('moonshine::ui.resource.account.bio'),
                    'bio')
                    ->required(),
                Text::make(
                    static fn() => __('moonshine::ui.resource.account.username'),
                    'username')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.username')),
                Text::make(
                    static fn() => __('moonshine::ui.resource.account.email'),
                    'email')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.account.email')),
                Password::make('Password'),
                PasswordRepeat::make('Password repeat', 'password_repeat')
            ]),
        ];
    }

    public function detailFields(): array
    {
        return [
                Text::make(static fn() => __('moonshine::ui.resource.account.name'), 'name'),
                Text::make(static fn() => __('moonshine::ui.resource.account.last_name'), 'last_name'),
                Text::make(static fn() => __('moonshine::ui.resource.account.control_number'), 'control_number'),
                Select::make(static fn() => __('moonshine::ui.resource.account.sex'), 'sex')
                ->options([
                    'mujer' => 'Mujer',
                    'hombre' => 'Hombre',
                    'binario' => 'Binario',
                    'otro' => 'Otro',
                ])
                ->default('otro'),
                Number::make(static fn() => __('moonshine::ui.resource.account.age'), 'age')
                ->buttons()
                ->min(1)
                ->max(190)
                ->default(18),
                Number::make(static fn() => __('moonshine::ui.resource.account.grade'), 'grade')
                ->buttons()
                ->min(1)
                ->max(20)
                ->default(1),
                Select::make(static fn() => __('moonshine::ui.resource.account.shift'), 'shift')
                ->options([
                    'matutino' => 'Matutino',
                    'vespertino' => 'Vespertino',
                ])
                ->default('matutino'),
                Image::make(static fn() => __('moonshine::ui.resource.account.photo'), 'photo'),
                Text::make(static fn() => __('moonshine::ui.resource.account.username'), 'username'),
                Text::make(static fn() => __('moonshine::ui.resource.account.email'), 'email'),
        ];
    }

    public function indexFields(): array
    {
        return [
                Text::make(static fn() => __('moonshine::ui.resource.account.name'), 'name'),
                Text::make(static fn() => __('moonshine::ui.resource.account.last_name'), 'last_name'),
                Text::make(static fn() => __('moonshine::ui.resource.account.control_number'), 'control_number'),
                Select::make(static fn() => __('moonshine::ui.resource.account.sex'), 'sex')
                ->options([
                    'mujer' => 'Mujer',
                    'hombre' => 'Hombre',
                    'binario' => 'Binario',
                    'otro' => 'Otro',
                ])
                ->default('otro'),
                Number::make(static fn() => __('moonshine::ui.resource.account.age'), 'age')
                ->buttons()
                ->min(1)
                ->max(190)
                ->default(18),
                Number::make(static fn() => __('moonshine::ui.resource.account.grade'), 'grade')
                ->buttons()
                ->min(1)
                ->max(20)
                ->default(1),
                Select::make(static fn() => __('moonshine::ui.resource.account.shift'), 'shift')
                ->options([
                    'matutino' => 'Matutino',
                    'vespertino' => 'Vespertino',
                ])
                ->default('matutino'),
                Image::make(static fn() => __('moonshine::ui.resource.account.photo'), 'photo'),
                Text::make(static fn() => __('moonshine::ui.resource.account.username'), 'username'),
                Text::make(static fn() => __('moonshine::ui.resource.account.email'), 'email'),
        ];
    }

    /**
     * @param Account $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [
            'name' => ['required', 'string', 'min:5'],
            'last_name' => ['required', 'string', 'min:5'],
            'control_number' => ['required', 'string', 'min:5'],
            'sex' => ['required', 'string', 'min:1'],
            'age' => ['required', 'numeric'],
            'grade' => ['required', 'numeric'],
            'shift' => ['required', 'min:1'],
            'bio' => ['required', 'string', 'min:5'],
            'username' => ['required', 'string', 'min:5'],
            'email' => ['required', 'email', 'min:5'],
        ];
    }

    public function validationMessages(): array
    {
        return [
            'name.required' => 'El campo nombres es obligatorio.',
            'last_name.required' => 'El campo apellidos es obligatorio.',
            'control_number.required' => 'El campo número de control es obligatorio.',
            'sex.required' => 'El campo sexo es obligatorio.',
            'age.required' => 'El campo edad es obligatorio.',
            'grade.required' => 'El campo grado es obligatorio.',
            'shift.required' => 'El campo turno es obligatorio.',
            'photo.required' => 'El campo foto es obligatorio.',
            'bio.required' => 'El campo bio es obligatorio.',
            'username.required' => 'El nombre de usuario descripción es obligatorio.',
            'email.required' => 'El campo correo electrónico es obligatorio.',
        ];
    }

}
