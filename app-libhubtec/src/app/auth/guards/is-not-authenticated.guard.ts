import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

import { AuthService } from '../services/auth-service.service';
import { AuthStatus } from '../../shared/interfaces';

export const isNotAuthenticatedGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  if(authService.authStatus() ===  AuthStatus.checking) {
    return false;
  }

  if(authService.authStatus() === AuthStatus.authenticated) {
    router.navigateByUrl('/books/list');
    return false;
  }

  return true;
};
