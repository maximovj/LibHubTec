import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';
import { inject } from '@angular/core';
import { AuthStatus } from '../interfaces/auth-status.enum';

export const isAuthenticatedGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  if(authService.authStatus() ===  AuthStatus.checking) {
    return false;
  }

  if(authService.authStatus() === AuthStatus.notAuthenticated) {
    router.navigateByUrl('/login');
    return false;
  };

  return true;
};
