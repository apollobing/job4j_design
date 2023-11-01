insert into "role"("name") values ('admin');
insert into "role"("name") values ('moderator');
insert into "role"(name) values ('user');

insert into "rule"("name") values ('create');
insert into "rule"("name") values ('delete');
insert into "rule"("name") values ('edit');

insert into "role_rule"("role_id", "rule_id") values (1, 2);
insert into "role_rule"("role_id", "rule_id") values (3, 1);
insert into "role_rule"("role_id", "rule_id") values (2, 3);

insert into "user"("name", "role_id") values ('Bob', 1);
insert into "user"("name", "role_id") values ('Tom', 2);
insert into "user"("name", "role_id") values ('John', 3);

insert into "category"("name") values ('repairs');
insert into "category"("name") values ('washing');
insert into "category"("name") values ('painting');

insert into "state"("name") values ('new');
insert into "state"("name") values ('work');
insert into "state"("name") values ('done');

insert into "item"("name", "user_id", "category_id", "state_id") values ('Car repairs', 3, 1, 1);
insert into "item"("name", "user_id", "category_id", "state_id") values ('Car washing', 2, 2, 2);
insert into "item"("name", "user_id", "category_id", "state_id") values ('Car painting', 1, 3, 3);

insert into "attach"("name", "item_id") values ('photo1.jpg', 1);
insert into "attach"("name", "item_id") values ('photo2.jpg', 2);
insert into "attach"("name", "item_id") values ('photo3.jpg', 3);

insert into "comment"("name", "item_id") values ('Need to fix engine', 1);
insert into "comment"("name", "item_id") values ('Start to clean car', 2);
insert into "comment"("name", "item_id") values ('Finish car painting', 3);

select * from "role";
select * from "rule";
select * from "role_rule";
select * from "user";
select * from "category";
select * from "state";
select * from "item";
select * from "attach";
select * from "comment";
select * from "user" where "id" in (select "user_id" from "item");