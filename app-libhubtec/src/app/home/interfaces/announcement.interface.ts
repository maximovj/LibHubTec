export interface Announcement {
  id:          number;
  title:       string;
  content:     string;
  link:        null | string;
  tags:        null | string;
  isPublished: boolean;
  pictures:    string[];
  createdAt:   Date;
  updatedAt:   Date;
}
