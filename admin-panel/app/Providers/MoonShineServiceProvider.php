<?php

declare(strict_types=1);

namespace App\Providers;

use App\MoonShine\Resources\AccountResource;
use App\MoonShine\Resources\BookResource;
use App\MoonShine\Resources\RecoverAccountResource;
use App\MoonShine\Resources\ReserveBookResource;
use MoonShine\Providers\MoonShineApplicationServiceProvider;
use MoonShine\MoonShine;
use MoonShine\Menu\MenuGroup;
use MoonShine\Menu\MenuItem;
use MoonShine\Resources\MoonShineUserResource;
use MoonShine\Resources\MoonShineUserRoleResource;
use MoonShine\Contracts\Resources\ResourceContract;
use MoonShine\Menu\MenuElement;
use MoonShine\Pages\Page;
use Closure;

class MoonShineServiceProvider extends MoonShineApplicationServiceProvider
{
    /**
     * @return list<ResourceContract>
     */
    protected function resources(): array
    {
        return [
            new BookResource(),
            new ReserveBookResource(),
        ];
    }

    /**
     * @return list<Page>
     */
    protected function pages(): array
    {
        return [];
    }

    /**
     * @return Closure|list<MenuElement>
     */
    protected function menu(): array
    {
        return [
            MenuGroup::make(static fn() => __('moonshine::ui.resource.system'), [
                MenuItem::make(
                    static fn() => __('moonshine::ui.resource.admins_title'),
                    new MoonShineUserResource()
                ),
                MenuItem::make(
                    static fn() => __('moonshine::ui.resource.role_title'),
                    new MoonShineUserRoleResource()
                ),
            ]),
            MenuItem::make(
            static fn() => __('moonshine::ui.resource.book_title'),
            new BookResource()
            ),
            MenuItem::make(
            static fn() => __('moonshine::ui.resource.account_title'),
            new AccountResource()
            ),
            MenuItem::make(
                static fn() => __('moonshine::ui.resource.recover_account_title'),
                new RecoverAccountResource()
            ),
            MenuItem::make(static fn() => 'Reserve Books', new ReserveBookResource()),
        ];
    }

    /**
     * @return Closure|array{css: string, colors: array, darkColors: array}
     */
    protected function theme(): array
    {
        return [];
    }
}
