<?php

declare(strict_types=1);

namespace App\MoonShine\Pages;

use App\Models\Account;
use App\Models\Announcement;
use App\Models\Book;
use App\Models\NotificationAccount;
use App\Models\RecoverAccount;
use App\Models\ReserveBook;
use App\Models\Search;
use MoonShine\Pages\Page;
use MoonShine\Components\MoonShineComponent;
use MoonShine\Decorations\Block;
use MoonShine\Decorations\Column;
use MoonShine\Decorations\Divider;
use MoonShine\Decorations\Grid;
use MoonShine\Decorations\TextBlock;
use MoonShine\Metrics\DonutChartMetric;
use MoonShine\Metrics\ValueMetric;

class Dashboard extends Page
{
    /**
     * @return array<string, string>
     */
    public function breadcrumbs(): array
    {
        return [
            '#' => $this->title()
        ];
    }

    public function title(): string
    {
        return $this->title ?: 'Dashboard';
    }

    /**
     * @return list<MoonShineComponent>
     */
    public function components(): array
	{
		return [
            Grid::make([
                Column::make([
                    Block::make([
                        DonutChartMetric::make('Anuncios')
                            ->values([
                                'Publicados' => Announcement::isPublished()->count(),
                                'No publicados' => Announcement::isNotPublished()->count()
                            ])
                    ])
                ])->columnSpan(6),
                Column::make([
                    Block::make([
                        DonutChartMetric::make('Libros')
                            ->values([
                                'Libros disponibles' => Book::stock(),
                                'Libros reservados' => ReserveBook::isActive()->count(),
                            ])
                    ])
                ])->columnSpan(6),
                ValueMetric::make('Cuentas')
                    ->value(Account::count())
                    ->icon('heroicons.users')
                    ->columnSpan(6),
                ValueMetric::make('Recuperación de cuentas')
                    ->value(RecoverAccount::count())
                    ->icon('heroicons.user-group')
                    ->columnSpan(6),
                ValueMetric::make('Notificaciones')
                    ->value(NotificationAccount::count())
                    ->icon('heroicons.bell')
                    ->columnSpan(6),
                ValueMetric::make('Búsquedas')
                    ->value(Search::count())
                    ->icon('heroicons.magnifying-glass')
                    ->columnSpan(6),
                Column::make([
                    Divider::make('Otros'),
                ]),
                ValueMetric::make('Próximos ingresos')
                    ->value(ReserveBook::bookPrice())
                    ->icon('heroicons.currency-dollar')
                    ->columnSpan(6),
            ])
        ];
	}
}
