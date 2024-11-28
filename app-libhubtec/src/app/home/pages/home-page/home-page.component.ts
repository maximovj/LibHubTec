import { AnnouncementService } from './../../services/announcement-service.service';
import { CommonModule } from "@angular/common";
import { Component, inject, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";

import { GalleryImagesComponent } from "../../components/gallery-images/gallery-images.component";
import { CardModule } from "primeng/card";
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';
import { Announcement } from '../../interfaces';
import { LoadingComponent } from '../../../shared/components/loading/loading.component';
import { ChipModule } from 'primeng/chip';
import { ButtonModule } from 'primeng/button';

@Component({
  standalone: true,
  styles: ``,
  templateUrl: "./home-page.component.html",
  imports: [
    CommonModule,
    RouterModule,
    ButtonModule,
    CardModule,
    ChipModule,
    MenuBarComponent,
    LoadingComponent,
    GalleryImagesComponent,
  ],
})
export class HomePage implements OnInit {

  private announcementService = inject(AnnouncementService);

  public announcements !:Observable<any>;

  constructor() { }

  ngOnInit(): void {
    this.announcements = this.announcementService
      .listAllAnnountcements()
      .pipe(
        delay(1000),
        map( (data :Announcement[]) => {
          return ({ loading: false, data, err: null });
        }),
        startWith({ loading: true, data: null, err: null }),
        catchError( (err) => of({ loading: false, data: null, err }))
      );
  }

}
