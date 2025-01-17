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
use MoonShine\Decorations\Column;
use MoonShine\Decorations\Grid;
use MoonShine\Decorations\Heading;
use MoonShine\Enums\PageType;
use MoonShine\Fields\Text;
use MoonShine\Fields\Textarea;
use MoonShine\Fields\Image;
use MoonShine\Fields\Number;
use MoonShine\Fields\Preview;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;
use MoonShine\Metrics\ValueMetric;

/**
 * @extends ModelResource<Book>
 */

#[Icon('heroicons.book-open')]
class BookResource extends ModelResource
{
    protected string $model = Book::class;

    protected ?PageType $redirectAfterSave = PageType::INDEX;

    protected string $title = 'Libros';

    protected bool $createInModal = true;

    protected bool $editInModal = true;

    protected bool $detailInModal = false;

    protected bool $errorsAbove = true;

    public function title(): string
    {
        return __('moonshine::ui.resource.book_title');
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

    public function metrics(): array
    {
        return [
            Grid::make([
                Column::make([
                    Block::make([
                        ValueMetric::make(__('moonshine::ui.resource.book_metric.book_count'))
                        ->value(Book::count())
                        ->columnSpan(6),
                    ])
                ])->columnSpan(6),
                Column::make([
                    Block::make([
                        ValueMetric::make(__('moonshine::ui.resource.book_metric.book_stock'))
                        ->value(Book::stock())
                        ->columnSpan(6),
                    ])
                ])->columnSpan(6),
            ]),
        ];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                Heading::make(__('moonshine::ui.all_fields_required')),
                ID::make()->sortable(),
                Image::make(
                    static fn() => __('moonshine::ui.resource.book.cover'),
                    'cover')
                    ->disk(config('moonshine.disk', 'public'))
                    ->dir('books')
                    ->allowedExtensions(['jpg', 'png', 'jpeg'])
                    ->nullable(),
                Text::make(
                    static fn() => __('moonshine::ui.resource.book.title'),
                    'title')
                    ->placeholder(__('moonshine::ui.resource.book.title'))
                    ->required(),
                Text::make(
                    static fn() => __('moonshine::ui.resource.book.author'),
                    'author')
                    ->placeholder(__('moonshine::ui.resource.book.author'))
                    ->required(),
                Textarea::make(
                    static fn() => __('moonshine::ui.resource.book.summary'),
                    'summary'),
                Textarea::make(
                    static fn() => __('moonshine::ui.resource.book.description'),
                    'description'),
                Number::make(
                    static fn() => __('moonshine::ui.resource.book.stock'),
                    'stock')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.book.stock'))
                    ->min(0)
                    ->buttons(),
                Number::make(
                    static fn() => __('moonshine::ui.resource.book.price'),
                    'price')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.book.price'))
                    ->min(0)
                    ->buttons(),
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
            Image::make(
                static fn() => __('moonshine::ui.resource.book.cover'),
                'cover')
                ->disk(config('moonshine.disk', 'public'))
                ->dir('books')
                ->allowedExtensions(['jpg', 'png', 'jpeg'])
                ->required(),
            Text::make(
                static fn() => __('moonshine::ui.resource.book.title'),
                'title')
                ->placeholder(__('moonshine::ui.resource.book.title'))
                ->required(),
            Text::make(
                static fn() => __('moonshine::ui.resource.book.author'),
                'author')
                ->placeholder(__('moonshine::ui.resource.book.author'))
                ->required(),
            Number::make(
                static fn() => __('moonshine::ui.resource.book.stock'),
                'stock')
                ->required()
                ->placeholder(__('moonshine::ui.resource.book.stock'))
                ->min(0)
                ->buttons(),
            Number::make(
                static fn() => __('moonshine::ui.resource.book.price'),
                'price')
                ->required()
                ->placeholder(__('moonshine::ui.resource.book.price'))
                ->min(0)
                ->buttons(),
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
            'summary' => ['required', 'string', 'min:5'],
            'description' => ['required', 'string', 'min:5'],
        ];
    }

    public function validationMessages(): array
    {
        return [
            'title.required' => 'El campo título es obligatorio.',
            'author.required' => 'El campo autor es obligatorio.',
            'summary.required' => 'El campo resumen es obligatorio.',
            'description.required' => 'El campo descripción es obligatorio.',
        ];
    }
}
