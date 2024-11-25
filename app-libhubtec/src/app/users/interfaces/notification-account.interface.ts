import { NotificationAccountStatus } from "./notification-account-status.enum";

export interface NotificationAccount {
  id?:         number | null;
  subject?:    string | null;
  content?:    string | null;
  send_email?: boolean | null;
  status?:     NotificationAccountStatus | null;
  tags?:       string[] | null;
  createdAt?:  Date | null;
  updatedAt?:  Date | null;
}
