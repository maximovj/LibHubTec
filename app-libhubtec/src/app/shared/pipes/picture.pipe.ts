import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'picture',
  standalone: true,
})

export class PicturePipe implements PipeTransform {
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
