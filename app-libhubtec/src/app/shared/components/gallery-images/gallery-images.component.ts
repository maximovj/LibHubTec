import { LoadingComponent } from './../loading/loading.component';
import { CommonModule } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { CardModule } from "primeng/card";
import { GalleriaModule } from "primeng/galleria";
import { ImageModule } from "primeng/image";
import { LoadingImageComponent } from '../loading-image/loading-image.component';

@Component({
  selector: 'shared-gallery-images',
  standalone: true,
  styles: ``,
  templateUrl: "./gallery-images.component.html",
  imports: [
    CommonModule,
    CardModule,
    ImageModule,
    GalleriaModule,
    LoadingComponent,
    LoadingImageComponent,
  ]
})
export class GalleryImagesComponent implements OnInit {

  images: any[] | undefined;


  @Input("pictures") pictures :string[] = [];

  responsiveOptions: any[] = [
      {
          breakpoint: '1024px',
          numVisible: 5
      },
      {
          breakpoint: '768px',
          numVisible: 3
      },
      {
          breakpoint: '560px',
          numVisible: 1
      }
  ];

  constructor () { }

  ngOnInit(): void {
    this.images = this.pictures.map((x) => {
      return { itemImageSrc: x, alt: x  };
    });
  }

  public imgError(event: Event): void {
    const target = event.target as HTMLImageElement;
    target.src = 'images/no_thumbnail.jpg';
  }

}
