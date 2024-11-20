import { Component, computed, effect, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { PrimeNGConfig } from 'primeng/api';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { AuthService } from './auth/services/auth-service.service';
import { AuthStatus } from './shared/interfaces';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    ProgressSpinnerModule,
    ButtonModule,
    CommonModule,
    RouterOutlet,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private authService = inject(AuthService);
  private router = inject(Router);

  title = 'app-libhubtec';
  private primengConfig: PrimeNGConfig = inject(PrimeNGConfig);

  ngOnInit() {
    this.primengConfig.ripple = true;
  }

  public finishedAuthCheck = computed<boolean>(() => {
    if(this.authService.authStatus() === AuthStatus.checking) {
      return false;
    }

    return true;
  });

  public authCheckStatus = effect(() => {
    const status = this.authService.authStatus();
    switch(status) {
      case AuthStatus.checking:
        break;
      case AuthStatus.authenticated:
        this.router.navigateByUrl('/books/p/1');
        break;
      case AuthStatus.notAuthenticated:
        this.router.navigateByUrl('/login');
        break;
    }
  });
}
