import { CommonModule } from '@angular/common';
import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { RippleModule } from 'primeng/ripple';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { AuthService } from '../../../auth/services/auth-service.service';

@Component({
  selector: 'users-tiered-menu',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    TieredMenuModule,
    RippleModule,
  ],
  styles: `
    .menuitem-active {
      background-color: #E0E0E0;
      color: #4b5563;
    }
  `,
  templateUrl: './tiered-menu.component.html',
})

export class TieredMenuComponent implements OnInit {

  private service = inject(AuthService);

  public _itemActive = signal<string>('General');
  public itemActive = computed(()=> this._itemActive());

  public items: MenuItem[] | undefined;

  constructor() {
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
        },
        {
          label: 'Salir',
          icon: 'pi pi-sign-out',
          command: ()=> this.service.logout(),
        }
    ];
  }

  ngOnInit() { }

  setActive(label: string) :void
  {
    this._itemActive.set(label);
  }
}
