import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { Component, computed, effect, inject, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { PrimeNGConfig } from 'primeng/api';
import { MenuBarComponent } from './shared/components/menu-bar/menu-bar.component';
import { AuthService } from './auth/services/auth-service.service';
import { AuthStatus } from './auth/interfaces/auth-status.enum';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    ProgressSpinnerModule,
    ButtonModule,
    CommonModule,
    RouterOutlet,
    MenuBarComponent,
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
        this.router.navigateByUrl('/books/list');
        break;
      case AuthStatus.notAuthenticated:
        this.router.navigateByUrl('/auth/login');
        break;
    }
  });
}
