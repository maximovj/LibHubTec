import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { CardModule } from "primeng/card";
import { GalleriaModule } from "primeng/galleria";
import { ImageModule } from "primeng/image";

@Component({
  selector: 'home-images-previes',
  standalone: true,
  styles: ``,
  templateUrl: "./gallery-images.component.html",
  imports: [
    CommonModule,
    CardModule,
    ImageModule,
    GalleriaModule,
  ]
})
export class ImagesPreviewsComponent implements OnInit {

  images: any[] | undefined;

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

  constructor () {
    this.images = [
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+1',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+1',
        alt: 'Description for Image 1',
        title: 'Title 1'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+2',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+2',
        alt: 'Description for Image 2',
        title: 'Title 2'
      },
    ];
  }

  ngOnInit(): void {

  }

}
