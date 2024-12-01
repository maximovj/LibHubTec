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
                        DonutChartMetric::make(__('moonshine::ui.dasboard.announcement.title'))
                            ->values([
                                __('moonshine::ui.dasboard.announcement.is_published') => Announcement::isPublished()->count(),
                                __('moonshine::ui.dasboard.announcement.is_not_published') => Announcement::isNotPublished()->count()
                            ])
                    ])
                ])->columnSpan(6),
                Column::make([
                    Block::make([
                        DonutChartMetric::make(__('moonshine::ui.dasboard.book.title'))
                            ->values([
                                __('moonshine::ui.dasboard.book.stock') => Book::stock(),
                                __('moonshine::ui.dasboard.book.reserve_book') => ReserveBook::isActive()->count(),
                            ])
                    ])
                ])->columnSpan(6),
                ValueMetric::make(__('moonshine::ui.dasboard.metric.account'))
                    ->value(Account::count())
                    ->icon('heroicons.users')
                    ->columnSpan(6),
                ValueMetric::make(__('moonshine::ui.dasboard.metric.reserve_book'))
                    ->value(RecoverAccount::count())
                    ->icon('heroicons.user-group')
                    ->columnSpan(6),
                ValueMetric::make(__('moonshine::ui.dasboard.metric.notification'))
                    ->value(NotificationAccount::count())
                    ->icon('heroicons.bell')
                    ->columnSpan(6),
                ValueMetric::make(__('moonshine::ui.dasboard.metric.search'))
                    ->value(Search::count())
                    ->icon('heroicons.magnifying-glass')
                    ->columnSpan(6),
                Column::make([
                    Divider::make('Otros'),
                ]),
                ValueMetric::make(__('moonshine::ui.dasboard.metric.upcoming'))
                    ->value(ReserveBook::bookPrice())
                    ->icon('heroicons.currency-dollar')
                    ->columnSpan(6),
            ])
        ];
	}
}
