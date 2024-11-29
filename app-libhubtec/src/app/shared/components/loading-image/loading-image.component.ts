import { CommonModule } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { PicturePipe } from "../../pipes/picture.pipe";
import { ImageModule } from "primeng/image";

@Component({
  selector: 'shared-loading-image',
  standalone: true,
  styles: ``,
  template: `
      @if(pImageEnable) {
        <div class="card flex justify-content-center">
          <p-image
          [src]="imgSrc | picture"
          alt="Image"
          [height]="imgHeight"
          [imageClass]="imgClass"
          [imageStyle]="{ 'width': '100%', 'display' : 'block' }"
          [style]="{ 'width': '100%', 'display' : 'block' }"
          loading="lazy"
          [preview]="true"
          (imageError)="srcError($event)" />
        </div>
      } @else {
        <img
        [src]="imgSrc | picture"
        [class]="imgClass"
        [alt]="imgAlt"
        style="width: 100%; display: block;"
        (error)="srcError($event)" />
      }
  `,
  imports: [
    CommonModule,
    PicturePipe,
    ImageModule,
  ]
})
export class LoadingImageComponent implements OnInit {

  @Input("imgSrc")
  public imgSrc ?:string;

  @Input("imgClass")
  public imgClass ?:string;

  @Input("imgAlt")
  public imgAlt ?:string | null = this.imgSrc;

  @Input("pImageEnable")
  public pImageEnable ?:boolean;

  @Input("imgHeight")
  public imgHeight ?:string;

  constructor () { }

  ngOnInit(): void {
    console.log('imgSRC: ',this.imgSrc)
  }

  public srcError(event: Event): void {
    const target = event.target as HTMLImageElement;
    target.src = 'images/no_thumbnail.jpg';
  }

}
