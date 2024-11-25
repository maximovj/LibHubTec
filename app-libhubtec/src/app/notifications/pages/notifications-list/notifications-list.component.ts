import { Component, OnInit } from "@angular/core";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";

@Component({
  standalone: true,
  styles: ``,
  template: `
    <shared-menu-bar></shared-menu-bar>
    <h1>Página de notificaciones</h1>
  `,
  imports: [
    MenuBarComponent,
  ],
})
export class NotificationsListPage implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
