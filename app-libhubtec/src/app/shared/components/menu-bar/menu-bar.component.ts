import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import { AvatarGroupModule } from 'primeng/avatargroup';
import { AvatarModule } from 'primeng/avatar';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

import { AuthService } from '../../../auth/services/auth-service.service';
import { TieredMenuService } from '../../../users/services/tiered-menu.service';

@Component({
  selector: 'shared-menu-bar',
  templateUrl: './menu-bar.component.html',
  standalone: true,
  imports: [
    AvatarGroupModule,
    AvatarModule,
    BadgeModule,
    CommonModule,
    InputTextModule,
    MenubarModule,
    RouterModule,
  ],
  styles: ``
})
export class MenuBarComponent implements OnInit {

  private router = inject(Router);
  private tieredMenuService = inject(TieredMenuService);
  private authService = inject(AuthService);
  public items :MenuItem[] | undefined;

  constructor() { }

  ngOnInit() {
    this.items = [
          {
              label: 'Inicio',
              icon: 'pi pi-home',
              routerLink: '/books/list',
          },
          {
              label: 'Favoritos',
              icon: 'pi pi-star',
              routerLink: '/books/list',
          },
          {
              label: 'Notificaciones',
              icon: 'pi pi-bell',
              badge: '3',
              command: () => {
                this.tieredMenuService.setActive('notifications');
                this.router.navigateByUrl(`/users/${this.authService.user()?.username}/notifications`);
              }
          }
      ];
  }

  get username(): string
  {
    return this.authService.user()?.username || '';
  }
}
