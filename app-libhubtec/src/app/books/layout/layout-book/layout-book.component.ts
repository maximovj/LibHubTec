import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { AvatarGroupModule } from 'primeng/avatargroup';
import { AvatarModule } from 'primeng/avatar';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

@Component({
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
  templateUrl: './layout-book.component.html',
})

export class LayoutBookComponent implements OnInit {

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
              routerLink: '/books/list',
          }
      ];
  }
}
