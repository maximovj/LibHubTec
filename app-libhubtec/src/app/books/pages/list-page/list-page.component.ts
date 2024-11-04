import { Component, OnInit, signal } from '@angular/core';
import { ImageModule } from 'primeng/image';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';

@Component({
  standalone: true,
  imports: [
    ImageModule,
    CardModule,
    ButtonModule,
  ],
  selector: 'books-list-page',
  templateUrl: './list-page.component.html',
})

export class BooksListComponent implements OnInit {

  public books = signal<string[]>(['Book #1', 'Book #2', 'Book #3', 'Book #4', 'Book #5', 'Book #6']);

  constructor() { }

  ngOnInit() { }
}
