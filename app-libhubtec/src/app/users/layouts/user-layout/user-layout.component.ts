import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';

import { TieredMenuModule } from 'primeng/tieredmenu';

@Component({
  standalone: true,
  imports: [
    RouterModule,
    TieredMenuModule,
  ],
  templateUrl: 'user-layout.component.html'
})

export class UserLayoutComponent implements OnInit {

  public items: MenuItem[] | undefined;

  constructor() { }

  ngOnInit() {
    this.items = [
          {
              label: 'General',
              icon: 'pi pi-user',
              routerLink: 'settings',
            },
            {
              label: 'Books',
              icon: 'pi pi-book',
              routerLink: 'my-books',
            },
            {
              label: 'Search',
              icon: 'pi pi-search',
              routerLink: 'my-search',
          },
          {
              separator: true
          },
          {
              label: 'Share',
              icon: 'pi pi-share-alt',
          }
      ];
  }
}
