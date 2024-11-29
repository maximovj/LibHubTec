import { Routes } from '@angular/router';
import { isAuthenticatedGuard } from './auth/guards/is-authenticated.guard';
import { isNotAuthenticatedGuard } from './auth/guards/is-not-authenticated.guard';
import { BookDetailsResolve } from './books/resolvers/book-details.resolve';

export const routes: Routes = [
  {
    path: 'login',
    canActivate: [isNotAuthenticatedGuard],
    loadComponent : () => import('./auth/pages/login/login-page.component').then( c => c.LoginPageComponent),
  },
  {
    path: 'forget-password',
    canActivate: [isNotAuthenticatedGuard],
    loadComponent : () => import('./auth/pages/forget-password/forget-password.component').then( c => c.ForgetPasswordComponent),
  },
  {
    path: 'auth',
    canActivate: [isNotAuthenticatedGuard],
    loadComponent: () => import('./auth/layout/auth-layout/auth-layout.component').then( c => c.LayoutAuthComponent),
    children: [
      {
        path: 'recover',
        children: [
          {
            path: 'account',
            loadComponent : () => import('./auth/pages/auth-layout/recover-account/recover-account.component').then( c => c.RecoverAccountComponent),
          },
        ],
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'recover',
      }
    ]
  },
  {
    path: 'books',
    canActivate: [isAuthenticatedGuard],
    loadComponent: () => import('./books/layout/layout-book/layout-book.component').then( c => c.LayoutBookComponent),
    children: [
      {
        path: 'list',
        loadComponent: () => import('./books/pages/list-page/list-page.component').then( c => c.BooksListComponent),
      },
      {
        path: 'p',
        children: [
          {
            path: ':id',
            resolve: { book_details: BookDetailsResolve },
            loadComponent: () => import('./books/pages/book-details/book-details.component').then( c => c.BookDetailsComponent),
          }
        ],
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'list',
      }
    ]
  },
  {
    path: "home",
    canActivate: [isAuthenticatedGuard],
    loadComponent: () => import('./home/pages/home-page/home-page.component').then( c => c.HomePage),
  },
  {
    path: 'users',
    canActivate: [isAuthenticatedGuard],
    children: [
      {
        path: ':username',
        loadComponent : () => import('./users/layouts/user-layout/user-layout.component').then( c => c.UserLayoutComponent),
        children: [
          {
            path: 'settings',
            loadComponent: () => import('./users/pages/user-settigs/user-settings.component').then( c => c.UserSettingsComponent),
          },
          {
            path: 'my-books',
            loadComponent: () => import('./users/pages/user-books/user-books.component').then( c => c.UserBooksComponent),
          },
          {
            path: 'my-search',
            loadComponent: () => import('./users/pages/user-search/user-search.component').then( c => c.UserSearchComponent),
          },
          {
            path: 'notifications',
            loadComponent: () => import('./users/pages/user-notifications/user-notifications.component').then( c => c.UserNotificationsComponent ),
          },
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'settings',
          }
        ]
      },
    ]
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  }
];
