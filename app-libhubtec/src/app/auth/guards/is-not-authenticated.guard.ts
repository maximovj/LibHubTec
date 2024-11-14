import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';

export const isNotAuthenticatedGuard: CanActivateFn = (route, state) => {
  const service = inject(AuthService);
  const router = inject(Router);

  if(service.isAuthenticated()) {
    router.navigateByUrl('/auth/login');
    return false;
  }

  return true;
};
