import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';
import { AuthStatus } from '../interfaces/auth-status.enum';

export const isNotAuthenticatedGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  console.log({ route, state, authStatus: authService.authStatus() });

  if(authService.authStatus() === AuthStatus.authenticated) {
    router.navigateByUrl('/books/list');
    return false;
  }

  return true;
};
