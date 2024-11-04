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
    loadComponent: () => import('./books/layout/layout-book.component').then( c => c.LayoutBookComponent),
    children: [
      {
        path: 'list',
        loadComponent: () => import('./books/pages/list-page.component').then( c => c.BooksListComponent),
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
