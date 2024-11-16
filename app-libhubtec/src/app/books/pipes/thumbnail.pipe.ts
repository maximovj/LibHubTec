import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'thumbnail',
  standalone: true,
})

export class ThumbnailPipe implements PipeTransform {
  private url_storage = 'http://localhost:8000/storage';

  transform(value: string | undefined | null): any {
    // [src]="'http://localhost:8000/storage/'.concat(item.thumbnail || '')"
    // src="images/no_thumbnail.jpg"


    if(!value) {
      return 'images/no_thumbnail.jpg';
    }

    return this.url_storage.concat('/').concat(value);
  }
}
