import { Component, inject, Input, OnInit } from "@angular/core";
import { CommonModule } from '@angular/common';
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';

import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { TooltipModule } from 'primeng/tooltip';
import { ChipModule } from 'primeng/chip';
import { ToastrService } from 'ngx-toastr';

import { LoadingComponent } from '../../../shared/components/loading/loading.component';
import { AuthService } from '../../../auth/services/auth-service.service';
import { NotificationsAccountService } from "../../services/notifications-account.service";
import { NotificationAccount, NotificationAccountStatus } from "../../interfaces";

@Component({
  selector: "user-notifications-list",
  standalone: true,
  templateUrl: "./notifications-list.component.html",
  imports: [
    CommonModule,
    LoadingComponent,
    CardModule,
    ButtonModule,
    DividerModule,
    TooltipModule,
    ChipModule,
  ],
})
export class NotificationsListComponent implements OnInit {

  private toastrService = inject(ToastrService);

  private authService = inject(AuthService);

  private notificationAccountService = inject(NotificationsAccountService);

  public listNotifications$ ?:Observable<any>;

  @Input("styleClassGrid")
  public styleClassGrid :string = "col-12 md:col-6";

  constructor() { }

  ngOnInit(): void
  {
    const user = this.authService.user();
    this.listNotifications$ = this.notificationAccountService
      .loadNotificationsAccount(user)
      .pipe(
        map( (data: NotificationAccount[]) => {
          console.log(data);
          return { loading: false, data, err: null  };
        }),
        startWith({ loading: true, data: null, err: null }),
        catchError((err) => of({ loading: false, data: null, err  }) )
      );
  }

  onBtnMarkLikeRead( notificationAccount:NotificationAccount) :void
  {
    if(notificationAccount.status !== NotificationAccountStatus.read) {
      this.notificationAccountService
      .readNotification(notificationAccount)
      .subscribe({
        next: (item) => {
          notificationAccount.status = item?.status;
          notificationAccount.updatedAt = item?.updatedAt;
          this.toastrService.success("Notificación marcado como leído");
        },
        error: () => {
          this.toastrService.error("Oops hubo un error durante la operación");
        },
      });
    }
  }

}
