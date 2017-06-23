
    create table Documents (
        id int8 not null,
        date timestamp,
        name varchar(255),
        owner_id int8,
        primary key (id)
    );

    create table User_roles (
        User_id int8 not null,
        roles varchar(255)
    );

    create table additional (
        id int8 not null,
        name varchar(255),
        requirement boolean not null,
        type varchar(255),
        application_id int8,
        department_id int8,
        primary key (id)
    );

    create table applications (
        id int8 not null,
        Submit_on varchar(255),
        gpa float8,
        gre float8,
        Condition boolean not null,
        Denied boolean not null,
        New boolean not null,
        PendingReview boolean not null,
        RecommendAdmit boolean not null,
        name varchar(255),
        term varchar(255),
        tofel float8,
        applicant_id int8,
        department_id int8,
        program_id int8,
        transcript_id int8,
        primary key (id)
    );

    create table applications_additional (
        applications_id int8 not null,
        additional_id int8 not null
    );

    create table degrees (
        id int8 not null,
        name varchar(255),
        school varchar(255),
        year int4,
        application_id int8,
        primary key (id)
    );

    create table departments (
        id int8 not null,
        Name varchar(255),
        primary key (id)
    );

    create table programs (
        id int8 not null,
        name varchar(255),
        department_id int8,
        primary key (id)
    );

    create table users (
        id int8 not null,
        Email varchar(255),
        FirstName varchar(255),
        LastName varchar(255),
        Password varchar(255),
        citizenship varchar(255),
        dateofbirth varchar(255),
        gender varchar(255),
        phone varchar(255),
        primary key (id)
    );

    alter table applications_additional 
        add constraint UK_7xwb1ho1qsgdwsld75f38fkoi unique (additional_id);

    alter table Documents 
        add constraint FK_402y39hfhovfan532flt0k7vf 
        foreign key (owner_id) 
        references users;

    alter table User_roles 
        add constraint FK_9npctppqlup1uag8ek04qpmie 
        foreign key (User_id) 
        references users;

    alter table additional 
        add constraint FK_ggd5nle9vrk738skbwyg2c4us 
        foreign key (application_id) 
        references applications;

    alter table additional 
        add constraint FK_14x5ea23ti48wx3gio084kgdi 
        foreign key (department_id) 
        references departments;

    alter table applications 
        add constraint FK_lmcomkxa52fc4kw1pgin4ov1i 
        foreign key (applicant_id) 
        references users;

    alter table applications 
        add constraint FK_78su0wnn02817h354falvtovc 
        foreign key (department_id) 
        references departments;

    alter table applications 
        add constraint FK_fvv8mt4q3l0jlgem0374rwfb5 
        foreign key (program_id) 
        references programs;

    alter table applications 
        add constraint FK_empb0c3eq0m4chyq8mfcw5fn2 
        foreign key (transcript_id) 
        references Documents;

    alter table applications_additional 
        add constraint FK_7xwb1ho1qsgdwsld75f38fkoi 
        foreign key (additional_id) 
        references additional;

    alter table applications_additional 
        add constraint FK_1lo3wxc1x7biuxkh69tgu8xmd 
        foreign key (applications_id) 
        references applications;

    alter table degrees 
        add constraint FK_rqi4wrhhtwwk6r9c9hcjyext9 
        foreign key (application_id) 
        references applications;

    alter table programs 
        add constraint FK_t38cee5jtiwtw07papp2rjlca 
        foreign key (department_id) 
        references departments;

    create sequence hibernate_sequence;
