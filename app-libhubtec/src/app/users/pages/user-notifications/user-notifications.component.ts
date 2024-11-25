import { Component, OnInit } from "@angular/core";
import { NotificationsListComponent } from "../../../notifications/components/notifications-list/notifications-list.component";

@Component({
  standalone: true,
  styles: ``,
  templateUrl: "./user-notifications.component.html",
  imports: [
    NotificationsListComponent,
  ],
})
export class UserNotificationsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
