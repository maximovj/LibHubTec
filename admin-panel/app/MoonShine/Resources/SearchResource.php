<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use Illuminate\Database\Eloquent\Model;
use App\Models\Search;
use Illuminate\Support\Facades\Request;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Fields\Number;
use MoonShine\Fields\Relationships\BelongsTo;
use MoonShine\Fields\Select;
use MoonShine\Fields\Slug;
use MoonShine\Fields\Text;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;

/**
 * @extends ModelResource<Search>
 */
#[Icon('heroicons.star')]
 class SearchResource extends ModelResource
{
    protected string $model = Search::class;

    protected string $title = 'Searches';

    protected string $column = 'id';

    protected bool $createInModal = false;

    protected bool $editInModal = false;

    protected bool $detailInModal = false;

    protected bool $isAsync = true;

    /**
     * @return string[]
     */
    public function getActiveActions(): array
    {
        return [];
    }

    public function title(): string
    {
        return __('moonshine::ui.resource.search_title');
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
                BelongsTo::make(
                    static fn() => __('moonshine::ui.resource.search.account'),
                    'account',
                    fn(Model $item) => "$item->name",
                    new SearchResource())
                    ->disabled()
                    ->withImage('photo','public','accounts'),
                Select::make(
                    static fn() => __('moonshine::ui.resource.search.query'),
                    'query')
                    ->options([
                        'q' => 'q',
                        'title' => 'title',
                        'author' => 'author',
                    ]),
                Text::make(
                    static fn() => __('moonshine::ui.resource.search.search'),
                    'search')
                    ->nullable(),
                Text::make(
                    static fn() => __('moonshine::ui.resource.search.base_url'),
                    'base_url')
                    ->readonly()
                    ->nullable()
                    ->default('/v1/books/search')
                    ->onApply(function(Model $item){
                        $base_url = "/v1/books/search?:query=:search";
                        $base_url = str_replace(':query', $item->query, $base_url);
                        $base_url = str_replace(':search', urlencode($item->search), $base_url);
                        $item->base_url =  $base_url;
                        return $item;
                    }),
                Number::make(
                    static fn() => __('moonshine::ui.resource.search.result'),
                    'result')
                    ->readonly()
                    ->min(0)
                    ->step(1)
                    ->default(0)
                    ->buttons(),
            ]),
        ];
    }

    /**
     * @param Search $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [];
    }
}
