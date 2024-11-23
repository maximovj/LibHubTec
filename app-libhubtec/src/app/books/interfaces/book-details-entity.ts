import { BookEntity } from "./book-entity.interface";

export interface BookDetailsEntity {
  reserve_book_id?:   number | null;
  account_id?:        number | null;
  book_id?:           number | null;
  date_from?:         Date | null;
  date_to?:           Date | null;
  isReserved?:        boolean | null,
  book:              BookEntity;
};
