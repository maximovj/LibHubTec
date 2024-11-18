import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';
import { inject } from '@angular/core';
import { AuthStatus } from '../interfaces/auth-status.enum';

export const isAuthenticatedGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  console.log({ route, state, authStatus: authService.authStatus() });

  if(authService.authStatus() ===  AuthStatus.checking) {
    //router.navigateByUrl('/auth/login'); // Página de carga
    return false;
  }

  if(authService.authStatus() === AuthStatus.notAuthenticated) {
    router.navigateByUrl('/auth/login');
    return false;
  };

  return true;
};
