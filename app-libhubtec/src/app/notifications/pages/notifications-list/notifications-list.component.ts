import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../../../auth/services/auth-service.service';
import { Component, inject, OnInit } from "@angular/core";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";
import { NotificationsAccountService } from "../../services/notifications-account.service";
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from '../../../shared/components/loading/loading.component';
import { NotificationAccount, NotificationAccountStatus } from '../../interfaces';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { TooltipModule } from 'primeng/tooltip';
import { ChipModule } from 'primeng/chip';

@Component({
  standalone: true,
  styles: `
    ::ng-deep p-chip.text-sm .p-chip-text {
      font-size: 12px !important;
    }
    .custom-chip .p-chip-text {
      font-size: 12px !important;
    }
  `,
  templateUrl: "./notifications-list.component.html",
  imports: [
    CommonModule,
    LoadingComponent,
    CardModule,
    ButtonModule,
    DividerModule,
    MenuBarComponent,
    TooltipModule,
    ChipModule,
  ],
})
export class NotificationsListPage implements OnInit {

  private toastrService = inject(ToastrService);

  private authService = inject(AuthService);

  private notificationAccountService = inject(NotificationsAccountService);

  public listNotifications$ ?:Observable<any>;

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
