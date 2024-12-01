<?php

declare(strict_types=1);

namespace App\MoonShine\Resources;

use Carbon\Carbon;
use App\Models\Account;
use App\Models\Book;
use Illuminate\Database\Eloquent\Model;
use App\Models\ReserveBook;
use Illuminate\Support\Facades\Request;
use MoonShine\Attributes\Icon;
use MoonShine\Resources\ModelResource;
use MoonShine\Decorations\Block;
use MoonShine\Fields\ID;
use MoonShine\Fields\Field;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Decorations\Heading;
use MoonShine\Enums\PageType;
use MoonShine\Fields\DateRange;
use MoonShine\Fields\Fields;
use MoonShine\Fields\Number;
use MoonShine\Fields\Relationships\BelongsTo;
use MoonShine\Fields\Text;
use MoonShine\Handlers\ExportHandler;
use MoonShine\Handlers\ImportHandler;
use MoonShine\Fields\Select;
use MoonShine\Fields\Switcher;

/**
 * @extends ModelResource<ReserveBook>
 */
#[Icon('heroicons.star')]
class ReserveBookResource extends ModelResource
{
    protected string $model = ReserveBook::class;

    protected ?PageType $redirectAfterSave = PageType::INDEX;

    protected string $title = 'ReserveBooks';

    protected bool $createInModal = false;

    protected bool $editInModal = false;

    protected bool $detailInModal = false;

    protected bool $isAsync = true;

    public function title(): string
    {
        return __('moonshine::ui.resource.reserve_book_title');
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
     * @return string[]
     */
    public function getActiveActions(): array
    {
        return ['create', 'view', 'update', 'delete', 'massDelete'];
    }

    /**
     * @return list<MoonShineComponent|Field>
     */
    public function fields(): array
    {
        return [
            Block::make([
                Heading::make('* Todos los campos son obligatorios'),
                ID::make()->sortable(),
                BelongsTo::make(
                    static fn() => __('moonshine::ui.resource.reserve_book.book_id'),
                    'book',
                    fn($item) => "$item->title | $item->author",
                    new BookResource())
                    ->required()
                    ->withImage('thumbnail','public','books')
                    ->default(Book::find(1))
                    ->setValue(Book::find(1))
                    ->searchable()
                    ->reactive(function(Fields $fields, ?string $value): Fields {
                        $book = Book::find($value);
                        if($book){
                            $fields->findByColumn('book_title')?->setValue($book->title);
                            $fields->findByColumn('book_author')?->setValue($book->author);
                            $fields->findByColumn('book_stock')?->setValue($book->stock);
                            $fields->findByColumn('book_price')?->setValue($book->price);
                        }
                        return $fields;
                    }),
                BelongsTo::make(
                    static fn() => __('moonshine::ui.resource.reserve_book.account_id'),
                    'account',
                    fn($item) => " $item->name $item->last_name | $item->email",
                    new AccountResource())
                    ->required()
                    ->withImage('photo','public','accounts')
                    ->default(Account::find(1))
                    ->setValue(Account::find(1))
                    ->searchable()
                    ->reactive(function(Fields $fields, ?string $value): Fields {
                        $account = Account::find($value);
                        if($account){
                            $fields->findByColumn('account_username')?->setValue($account->username);
                            $fields->findByColumn('account_email')?->setValue($account->email);
                            $fields->findByColumn('account_name')?->setValue($account->name);
                            $fields->findByColumn('account_last_name')?->setValue($account->last_name);
                        }
                        return $fields;
                    }),

                // Información de la cuenta
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.account_username'), 'account_username')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.account_username'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->account_username = $item->account->username)
                    ->readonly(),
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.account_email'), 'account_email')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.account_email'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->account_email = $item->account->email)
                    ->readonly(),
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.account_name'), 'account_name')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.account_name'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->account_name = $item->account->name)
                    ->readonly(),
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.account_last_name'), 'account_last_name')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.account_last_name'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->account_last_name = $item->account->last_name)
                    ->readonly(),

                // Información del libro
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.book_title'), 'book_title')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.book_title'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->book_title = $item->book->title)
                    ->readonly(),
                Text::make(static fn() => __('moonshine::ui.resource.reserve_book.book_author'), 'book_author')
                    ->required()
                    ->placeholder(__('moonshine::ui.resource.reserve_book.book_author'))
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->book_author = $item->book->author)
                    ->readonly(),
                //Text::make('Precio del libro', 'book_price')->reactive()->onApply(fn(Model $item) => $item->book_price = 100)->readonly(),
                //Text::make('Cantidad de libros', 'book_count')->reactive()->onApply(fn(Model $item) => $item->book_count = 1)->readonly(),
                //Text::make('Nombre del autor', 'book_author', fn(Model $item) => $item->book_author = $item->book->author), // Este solo aparece en index y view
                Number::make(static fn() => __('moonshine::ui.resource.reserve_book.book_price'), 'book_price')
                    ->required()
                    ->min(0)
                    ->placeholder(__('moonshine::ui.resource.reserve_book.book_price'))
                    ->buttons()
                    ->reactive()
                    ->onApply(fn(Model $item) => $item->book_price = $item->book->price)
                    ->readonly(),
                /*
                Number::make(static fn() => __('moonshine::ui.resource.reserve_book.book_count'), 'book_count')
                    ->required()
                    ->min(0)
                    ->placeholder(__('moonshine::ui.resource.reserve_book.book_count'))
                    ->buttons(),
                */
                DateRange::make(static fn() => __('moonshine::ui.resource.reserve_book.date_range'))
                    ->required()
                    ->fromTo('date_from', 'date_to')
                    ->min(Carbon::now()->format('Y-m-d'))
                    ->max(Carbon::now()->addDays(15)->format('Y-m-d')),
                Select::make(static fn() => __('moonshine::ui.resource.reserve_book.status._'), 'status')
                    ->options([
                        'pending' => __('moonshine::ui.resource.reserve_book.status.pending'),
                        'processing' => __('moonshine::ui.resource.reserve_book.status.processing'),
                        'paid' => __('moonshine::ui.resource.reserve_book.status.paid'),
                        'debt' => __('moonshine::ui.resource.reserve_book.status.debt'),
                    ]),
                Switcher::make(static fn() => __('moonshine::ui.resource.reserve_book.active'), 'active')
            ]),
        ];
    }

    /**
     * @param ReserveBook $item
     *
     * @return array<string, string[]|string>
     * @see https://laravel.com/docs/validation#available-validation-rules
     */
    public function rules(Model $item): array
    {
        return [];
    }
}
