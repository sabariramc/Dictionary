
RENAME CTX_USE TO EXP_UNT;

ALTER TABLE EXP_UNT
RENAME COLUMN CGSEQ TO EXPSEQ;

ALTER TABLE CTX_OF_USE 
DROP CONSTRAINT FK_CTX_OF_USE__CTX_GRP;

ALTER TABLE CTX_OF_USE
RENAME COLUMN CGSEQ TO EXPSEQ;

DELETE EXP_UNT;

ALTER TABLE EXP_UNT
ADD IFLAG CHAR(1 CHAR) 
CONSTRAINT NN_EXP_UNT__IFLAG NOT NULL 
CONSTRAINT CK_EXP_UNT__IFLAG CHECK (IFLAG IN('Y','N')) ;

ALTER TABLE EXP_UNT
RENAME CONSTRAINT PK_CTX_GRP TO PK_EXP_UNT;

ALTER TABLE EXP_UNT
RENAME CONSTRAINT FK_CTX_GRP__PS_CODE TO FK_EXP_UNT__PS_CODE;

ALTER TABLE EXP_UNT
RENAME CONSTRAINT FK_CTX_GRP__WORD TO FK_EXP_UNT__WORD;

ALTER TABLE EXP_UNT
RENAME CONSTRAINT NN_CTX_GRP__PCODE TO NN_EXP_UNT__PCODE;

ALTER TABLE CTX_MEANG
RENAME COLUMN CGSEQ TO EXPSEQ;

ALTER TABLE IDIOM
DROP CONSTRAINT FK_IDIOM__PS_CODE;

ALTER TABLE IDIOM
RENAME COLUMN PCODE TO EXPSEQ;

ALTER TABLE IDM_MEANG
RENAME COLUMN PCODE TO EXPSEQ;

ALTER TABLE IDM_MEANG
DROP CONSTRAINT FK_IDM_MEANG__IDIOM;

ALTER TABLE IDM_MEANG
ADD CONSTRAINT FK_IDM_MEANG__IDIOM
FOREIGN KEY(WID,EXPSEQ,ISEQ) REFERENCES IDIOM ON DELETE CASCADE;

ALTER TABLE WORD
DROP COLUMN IFLAG;


--USE THIS AFTER IMPORTING DATA TO EXP_UNT
ALTER TABLE CTX_OF_USE
ADD CONSTRAINT FK_CTX_OF_USE__EXP_UNT
FOREIGN KEY(WID,EXPSEQ) REFERENCES EXP_UNT ON DELETE CASCADE;

ALTER TABLE IDIOM
ADD CONSTRAINT FK_IDIOM__EXP_UNT
FOREIGN KEY(WID,EXPSEQ) REFERENCES EXP_UNT ON DELETE CASCADE;



ALTER TABLE EXP_UNT 
ADD CONSTRAINT AK_EXP_UNT__1
UNIQUE(WID,PCODE);