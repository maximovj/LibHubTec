import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'auth',
    loadComponent: () => import('./auth/layout/layout-auth.component').then( c => c.LayoutAuthComponent),
    children: [
      {
        path: 'login',
        loadComponent : () => import('./auth/pages/login/login-page.component').then( c => c.LoginPageComponent),
      },
      {
        path: 'recover-password',
        loadComponent : () => import('./auth/pages/recover-password/recover-password.component').then( c => c.RecoverPasswordComponent),
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'login',
      }
    ]
  },
  {
    path: 'books',
    loadComponent: () => import('./books/layout/layout-book/layout-book.component').then( c => c.LayoutBookComponent),
    children: [
      {
        path: 'list',
        loadComponent: () => import('./books/pages/list-page/list-page.component').then( c => c.BooksListComponent),
      },
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
            path: '',
            pathMatch: 'full',
            redirectTo: 'settings',
          }
        ]
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'list',
      }
    ]
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth',
  }
];
