export interface SearchEntity {
  id:         number;
  account_id: number;
  query:      string;
  base_url:   string;
  search:     string;
  result:     number;
  created_at: Date;
  updated_at: Date;
}
