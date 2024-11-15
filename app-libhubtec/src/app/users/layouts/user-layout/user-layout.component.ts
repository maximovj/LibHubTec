import { Component, OnInit } from '@angular/core';

import { MenuBarComponent } from '../../../shared/components/menu-bar/menu-bar.component';
import { CommonModule } from '@angular/common';
import { TieredMenuComponent } from '../../components/tiered-menu/tired-menu.component';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    TieredMenuComponent,
    RouterModule,
    CommonModule,
    MenuBarComponent,
  ],
  styles: `
  .active {
    color: red;
  }
  `,
  templateUrl: 'user-layout.component.html'
})

export class UserLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }

}
