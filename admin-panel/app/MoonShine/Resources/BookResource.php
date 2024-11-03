<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use Illuminate\Database\Eloquent\Model;
use App\Models\Book;
use Illuminate\Support\Facades\Request;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Fields\Text;
use MoonShine\Fields\Textarea;

/**
 * @extends ModelResource<Book>
 */

#[Icon('heroicons.book-open')]
class BookResource extends ModelResource
{
    protected string $model = Book::class;

    protected string $title = 'Libros';

    protected bool $createInModal = true;

    protected bool $editInModal = true;

    protected bool $detailInModal = false;

    protected bool $errorsAbove = true;

    public function title(): string
    {
        return __('moonshine::ui.resource.book_title');
    }

    public function redirectAfterSave(): string
    {
        $refer = Request::header('referer');
        return $refer ?? '/';
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                ID::make()->sortable(),
                Text::make(static fn() => __('moonshine::ui.resource.book.title'), 'title'),
                Text::make(static fn() => __('moonshine::ui.resource.book.author'), 'author'),
                Textarea::make(static fn() => __('moonshine::ui.resource.book.resume'), 'resume'),
                Textarea::make(static fn() => __('moonshine::ui.resource.book.description'), 'description'),
            ]),
        ];
    }

    /**
     * @param Book $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [
            'title' => ['required', 'string', 'min:5'],
            'author' => ['required', 'string', 'min:5'],
            'resume' => ['required', 'string', 'min:5'],
            'description' => ['required', 'string', 'min:5'],
        ];
    }

    public function validationMessages(): array
    {
        return [
            'title.required' => 'El campo título es obligatorio.',
            'author.required' => 'El campo autor es obligatorio.',
            'resume.required' => 'El campo resumen es obligatorio.',
            'description.required' => 'El campo descripción es obligatorio.',
        ];
    }
}
