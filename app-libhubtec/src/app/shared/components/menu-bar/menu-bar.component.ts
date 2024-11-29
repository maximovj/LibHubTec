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
import { User } from '../../interfaces';
import { PicturePipe } from '../../pipes/picture.pipe';
import { LoadingImageComponent } from '../loading-image/loading-image.component';

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
    PicturePipe,
    LoadingImageComponent,
  ],
  styles: ``
})
export class MenuBarComponent implements OnInit {

  private router = inject(Router);

  private tieredMenuService = inject(TieredMenuService);

  private authService = inject(AuthService);

  public items :MenuItem[] | undefined;

  public user ?: User | null = this.authService.user();

  constructor() { }

  ngOnInit() {
    ;
    this.items = [
          {
              label: 'Inicio',
              icon: 'pi pi-home',
              routerLink: '/home',
          },
          {
              label: 'Libros',
              icon: 'pi pi-book',
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

  get usernameLabel(): string
  {
    return this.authService.user()?.username?.charAt(0).toUpperCase() || 'A';
  }

  btnGoToSettings() : void
  {
    this.tieredMenuService.setActive('settings');
    this.router.navigateByUrl(`/users/${this.authService.user()?.username}/settings`);
  }
}
