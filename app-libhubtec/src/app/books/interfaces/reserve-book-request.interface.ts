export interface ReserveBookRequest
{
  reserve_book_id?:   number | null,
  account_id ?:       number | null;
  book_id?:           number | null;
  date_from?:         string | null;
  date_to?:           string | null;
}
