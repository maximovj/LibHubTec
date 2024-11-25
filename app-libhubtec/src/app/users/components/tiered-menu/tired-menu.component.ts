import { TieredMenuService } from '../../services/tiered-menu.service';
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

  private tieredMenuService = inject(TieredMenuService);
  private service = inject(AuthService);

  public routerLinkActive = computed(()=> this.tieredMenuService.routerLinkActive());

  public items: MenuItem[] | undefined;

  constructor() {
    this.items = [
        {
          label: 'General',
          icon: 'pi pi-user',
          routerLink: 'settings',
        },
        {
          label: 'Mis libros',
          icon: 'pi pi-book',
          routerLink: 'my-books',
        },
        {
          label: 'Notificaciones',
          icon: 'pi pi-bell',
          routerLink: 'notifications',
        },
        {
          label: 'Busqueda',
          icon: 'pi pi-search',
          routerLink: 'my-search',
        },
        {
          separator: true
        },
        {
          label: 'Compartir',
          icon: 'pi pi-share-alt',
        },
        {
          label: 'Salir',
          icon: 'pi pi-sign-out',
          command: ()=> this.service.logout(),
        }
    ];
  }

  ngOnInit() {
    this.tieredMenuService.activeRouterLinkByUrl();
  }

  setActive(routerLink: string) :void
  {
    this.tieredMenuService.setActive(routerLink);
  }

}
