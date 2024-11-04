import { Component, OnInit } from '@angular/core';

@Component({
  standalone: true,
  imports: [],
  selector: 'books-list-page',
  template: `
    <h1>Página de List de libros</h1>
  `,
})

export class BooksListComponent implements OnInit {
  constructor() { }

  ngOnInit() { }
}
