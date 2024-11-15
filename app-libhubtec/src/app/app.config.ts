import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withFetch } from '@angular/common/http';

import { provideToastr } from 'ngx-toastr';


export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withFetch()),
    provideAnimationsAsync(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideToastr({
      timeOut: 1700,
      extendedTimeOut: 1700,
      disableTimeOut: false,
      positionClass: 'toast-bottom-right',
      progressBar: true,
      closeButton: true,
      tapToDismiss: true,
    }),
    provideRouter(routes)]
};
