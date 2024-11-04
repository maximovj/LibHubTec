import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AvatarGroupModule } from 'primeng/avatargroup';
import { AvatarModule } from 'primeng/avatar';
import { BadgeModule } from 'primeng/badge';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

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

  public items :MenuItem[] | undefined;

  constructor() { }

  ngOnInit() {
    this.items = [ ];
  }
}
