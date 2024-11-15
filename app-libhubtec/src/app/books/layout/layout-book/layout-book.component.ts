import { AuthService } from './../../../auth/services/auth-service.service';
import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { AvatarGroupModule } from 'primeng/avatargroup';
import { AvatarModule } from 'primeng/avatar';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { MenuBarComponent } from '../../../shared/components/menu-bar/menu-bar.component';

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
    MenuBarComponent,
  ],
  templateUrl: './layout-book.component.html',
})

export class LayoutBookComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }

}
