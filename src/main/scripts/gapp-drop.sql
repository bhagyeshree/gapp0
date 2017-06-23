alter table applications_additional 
        drop constraint UK_7xwb1ho1qsgdwsld75f38fkoi;
    alter table Documents 
        drop constraint FK_402y39hfhovfan532flt0k7vf;

    alter table User_roles 
        drop constraint FK_9npctppqlup1uag8ek04qpmie;

    alter table additional 
        drop constraint FK_ggd5nle9vrk738skbwyg2c4us;

    alter table additional 
        drop constraint FK_14x5ea23ti48wx3gio084kgdi;

    alter table applications 
        drop constraint FK_lmcomkxa52fc4kw1pgin4ov1i;

    alter table applications 
        drop constraint FK_78su0wnn02817h354falvtovc;

    alter table applications 
        drop constraint FK_fvv8mt4q3l0jlgem0374rwfb5;

    alter table applications 
        drop constraint FK_empb0c3eq0m4chyq8mfcw5fn2;

    alter table applications_additional 
        drop constraint FK_7xwb1ho1qsgdwsld75f38fkoi;

    alter table applications_additional 
        drop constraint FK_1lo3wxc1x7biuxkh69tgu8xmd;

    alter table degrees 
        drop constraint FK_rqi4wrhhtwwk6r9c9hcjyext9;

    alter table programs 
        drop constraint FK_t38cee5jtiwtw07papp2rjlca ;





drop table programs;

drop table applications_additional;
drop table additional;
drop table degrees;
drop table applications;
drop table user_roles;
drop table Documents;
drop table users;
drop table departments;
drop sequence hibernate_sequence;





