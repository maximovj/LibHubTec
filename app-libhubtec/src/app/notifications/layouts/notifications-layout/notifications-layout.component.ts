import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  standalone: true,
  styles: ``,
  template: `
    <router-outlet></router-outlet>
  `,
  imports: [
    RouterModule,
  ]
})
export class NotificationsLayout {

}
