/*****************************************************************************************
 BIDE.cpp: implemented by Jianyong Wang, April 2003.
 ****************************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include <afx.h>
//#include <malloc.h>
#include <sys/types.h>
#include <sys/timeb.h>
#include <math.h>

/******************************************
 Global definitions
******************************************/
#define  NBACK			200


/******************************************
 Global variables
******************************************/
int  *n_fcs;		// Number of frequent closed itemsets with various length. n_fci[0] is the sum
FILE *f_output;		// File pointer of output file. output file name is "fci.dat"
char *SEQspec;		// Specification file name containing some global parameters and variables
double f_MIN_SUP;	// Relative support threshold
char F_SDB[50];		// File name of sequence database
int  MIN_SUP; 		// Absolute support threshold
int N_ITEMS_DB;		// Number of distinct items in the whole sequence database
int NUM_SEQ;		// Number of sequences in the whole sequence database
int MAX_LEN_SEQ;	// Maximal length of a sequence
int AVG_LEN_SEQ;	// Average length of a sequence
int *G_count;		// Global count used to find global or local frequent items
int *G_freq_idx;	// index of a frequent item to PROJ_DB
int *G_counted;		// Used to mark if an item has been already counted in the same sequence
int *SDB;			// Original sequence database
int *SEQ_idx;		// Index to each sequence in SDB
int END_SEQ;		// Used to mark the end of a sequence
int END_SDB;		// Used to mark the end of the whole database
int *backcnt[NBACK];// Count during backward scan
int empty[NBACK];

/******************************************
 Global structures
******************************************/
struct PROJ_SEQ{
	int *ppSeq;     // pointer to first element if each projection.
};

struct PROJ_DB{
	int  m_nPatLen;    // pattern length of projected database.
	int *m_pnPat;      // pattern of projected database.
	int  m_nSup;       // actual support found in dataset.
	int  lastID;	   // Keep track of the last sequenceID that supports this item
	int  Sup_cnt;      // current support found in dataset.
	struct PROJ_SEQ* projPnts;
};


/******************************************

 Module: routines

******************************************/

// open a file
FILE *openfile(char *f_name, char *mode){

	FILE *f;

	if ((f=fopen(f_name, mode))==NULL){
		printf("Error: cannot open file %s with mode %s!\n", f_name, mode);
		exit(-1);
	}
	else return(f);
}

// locate a block of memory
void *makememory(int size){

	void *pt;

	if ((pt=malloc(size))==NULL){
		printf("Error: cannot allocate memory with size %i!\n", size);
		exit(-1);
	}
	else return(pt);
}


// initialize the global parameters and variables
void init(){

	FILE *f_par;

	f_par=openfile(SEQspec, "r");
	fscanf(f_par, "%s", &F_SDB);
	fscanf(f_par, "%i", &N_ITEMS_DB);
	fscanf(f_par, "%i", &NUM_SEQ);
	fscanf(f_par, "%i", &MAX_LEN_SEQ);
	fscanf(f_par, "%i", &AVG_LEN_SEQ);
	fclose(f_par);

	MIN_SUP = (int) (f_MIN_SUP*NUM_SEQ+0.5);
	END_SEQ = N_ITEMS_DB+1;
	END_SDB = N_ITEMS_DB+2;

	return;
}


// Output the final result
void print_result(double t){

	int i;

	printf("BIDE: An efficient algorithm for mining frequent closed sequences.\n");
	printf("Data Mining research group.\n");
	printf("University of Illinois at Urbana-Champaign.\n");
	printf("ALL RIGHTS RESERVED!!\n\n");
	printf("Parameters are as follows:\n");
	printf("----------------------------------------------------------\n");
	printf("Transaction database:		%s\n", F_SDB);
	printf("Number of unique items in Database:	   				%i\n", N_ITEMS_DB);
	printf("Maximal length of a length:		%i\n", MAX_LEN_SEQ);
	printf("Number of transactions in Database:				%i\n", NUM_SEQ);
	printf("Relative support threshold:			%.3f\n", f_MIN_SUP);
	printf("Absolute support threshold:			%i\n", MIN_SUP);

	n_fcs[0]=0;
	printf("F.C.S Length\tNumber of F.C.S\n");
	for (i=1; i<=MAX_LEN_SEQ; i++)
		if (n_fcs[i]>0){
			printf("%i\t\t\t%i\n", i, n_fcs[i]);
			n_fcs[0]+=n_fcs[i];
		}
	printf("Total: %i\n", n_fcs[0]);

	printf("Runtime: %.3f sec.\n", t);
}

//recursively mine frequent closed sequences
void bid(struct PROJ_DB preProj, int semiClosed){
	int i, j, k, prfx_len, F_P, realClosed, L_freq;
	int *data;
	struct PROJ_DB *L_proj_DB;


	// Forward scan of the pseudo projected database to find the locally frequent items
	data=SDB;
	memset(G_count, 0, sizeof(int)*(N_ITEMS_DB+1));
	memset(G_counted, -1, sizeof(int)*(N_ITEMS_DB+1));
	for(i=0; i<preProj.m_nSup;i++){
		prfx_len=preProj.m_nPatLen;
		F_P=preProj.projPnts[i].ppSeq[prfx_len-1]+1;
		while(data[F_P]!=END_SEQ){
			if(data[F_P]<END_SEQ){
				if(G_counted[data[F_P]]<i){
					G_count[data[F_P]]++;
					G_counted[data[F_P]]=i;
				}
			}
			F_P++;
		}
	}
	realClosed=semiClosed;
	L_freq=0;
	for (i=0; i<N_ITEMS_DB; i++){
		if(G_count[i]>=MIN_SUP){
			L_freq++;
			if(G_count[i]==preProj.m_nSup)
				realClosed++;
		}
	}


	if(realClosed==0){
		// This is a real frequent closed sequence, output it!
		for(i=0;i<prfx_len;i++){
			fprintf(f_output, "%i ", preProj.m_pnPat[i]);
		}
		fprintf(f_output, ": %i\n", preProj.m_nSup);

		n_fcs[prfx_len]++; // output it
	}
	if(L_freq<=0)
		return;
	// Initialize the header tables for projected databases of frequent items
	L_proj_DB=(struct PROJ_DB *)makememory(sizeof(struct PROJ_DB)*L_freq);
	memset(G_freq_idx, -1, sizeof(int)*(N_ITEMS_DB+1));
	for(L_freq=i=0; i<N_ITEMS_DB; i++){
		if(G_count[i]>=MIN_SUP){
			L_proj_DB[L_freq].m_nPatLen=preProj.m_nPatLen + 1;
			L_proj_DB[L_freq].Sup_cnt=0;
			L_proj_DB[L_freq].lastID=-1;
			L_proj_DB[L_freq].m_pnPat=(int *)makememory(sizeof(int)*L_proj_DB[L_freq].m_nPatLen);

			for(j=0;j<preProj.m_nPatLen;j++){
				L_proj_DB[L_freq].m_pnPat[j]=preProj.m_pnPat[j];
			}
			L_proj_DB[L_freq].m_pnPat[j]=i;

			L_proj_DB[L_freq].m_nSup=G_count[i];
			L_proj_DB[L_freq].projPnts=(struct PROJ_SEQ*)makememory(sizeof(struct PROJ_SEQ)*L_proj_DB[L_freq].m_nSup);

			for(j=0;j<L_proj_DB[L_freq].m_nSup;j++){
				L_proj_DB[L_freq].projPnts[j].ppSeq=(int *)makememory(sizeof(int)*(L_proj_DB[L_freq].m_nPatLen+1));
				memset(L_proj_DB[L_freq].projPnts[j].ppSeq,0,sizeof(int)*(L_proj_DB[L_freq].m_nPatLen+1));
			}

			G_freq_idx[i]=L_freq;
			L_freq++;
		}
		else
			G_freq_idx[i]=-1;
	}

	// Scan the projected database of preProj once to build projected database for each locally frequent item
	int dataIDX;
	for(i=0; i<preProj.m_nSup;i++){
		prfx_len=preProj.m_nPatLen;
		F_P=preProj.projPnts[i].ppSeq[prfx_len-1]+1;

		while(data[F_P]!=END_SEQ){
			if((data[F_P]<END_SEQ)&&(G_freq_idx[data[F_P]]>=0)){
				dataIDX=G_freq_idx[data[F_P]];
				if(L_proj_DB[dataIDX].lastID<i){
					j=L_proj_DB[dataIDX].Sup_cnt;
					for(k=0; k<prfx_len; k++){
						L_proj_DB[dataIDX].projPnts[j].ppSeq[k]=preProj.projPnts[i].ppSeq[k];
					}
					L_proj_DB[dataIDX].projPnts[j].ppSeq[prfx_len]=F_P;
					L_proj_DB[dataIDX].projPnts[j].ppSeq[prfx_len+1]=F_P;
					L_proj_DB[dataIDX].Sup_cnt++;
					L_proj_DB[dataIDX].lastID=i;
				}
				else
					L_proj_DB[dataIDX].projPnts[L_proj_DB[dataIDX].Sup_cnt-1].ppSeq[prfx_len+1]=F_P;

			}
			F_P++;
		}
	}



	int l, bb, strt, nd, n_prefix, next, l_prefix;
	for (i=0; i<L_freq; i++){
		prfx_len=L_proj_DB[i].m_nPatLen;
		for(k=0; k<prfx_len; k++){
			memset(backcnt[k], 0, sizeof(int)*(N_ITEMS_DB+1));
			empty[k]=0;
		}
		// Backward scan the first appearance of SDB to prune the search space
		for(j=0;j<L_proj_DB[i].m_nSup;j++){
			bb=0;
			n_prefix=0;
			strt=L_proj_DB[i].projPnts[j].ppSeq[prfx_len-1-bb];
			if(j>0){
				while((bb<prfx_len)&&(empty[prfx_len-1-bb]==0)){
					while((bb<(prfx_len-1))&&(data[--strt]!=L_proj_DB[i].m_pnPat[prfx_len-2-bb]));
					bb++;
				}
				if(bb>=prfx_len)
					break;
			}

			while(bb<prfx_len){
				l=strt-1;//starting point
				next=0;
				l_prefix=n_prefix;
				if(bb<(prfx_len-1)){
					nd=L_proj_DB[i].projPnts[j].ppSeq[prfx_len-2-bb];//end point
					while(l>nd){
						if(backcnt[prfx_len-1-bb][data[l]]==j){
							backcnt[prfx_len-1-bb][data[l]]++;
							n_prefix++;
						}
						if((next==0)&&(data[l]==L_proj_DB[i].m_pnPat[prfx_len-2-bb])){
							strt=l;
							next=1;
						}
						l--;
					}
					//if(data[strt]!=L_proj_DB[i].m_pnPat[prfx_len-2-bb])
					if(next==0)
						strt=nd;
				}
				else{
					while((l>=0)&&(data[l]!=END_SEQ)){
						if(backcnt[prfx_len-1-bb][data[l]]==j){
							backcnt[prfx_len-1-bb][data[l]]++;
							n_prefix++;
						}
						l--;
					}
				}
				bb++;
				if(j==0)
					empty[prfx_len-bb]=n_prefix-l_prefix;
				if(j>0){
					l_prefix=n_prefix-l_prefix;
					if(empty[prfx_len-bb]>l_prefix)
						empty[prfx_len-bb]=l_prefix;
					while((bb<prfx_len)&&(empty[prfx_len-1-bb]==0)){
						while((bb<(prfx_len-1))&&(data[--strt]!=L_proj_DB[i].m_pnPat[prfx_len-2-bb]));
						bb++;
					}
				}
			}

			if(n_prefix==0)
				break;
		}


		if(n_prefix==0){//cannot be pruned
			// Check if it's closed by backward scanning of the last appearance of SDB
			for(k=0; k<prfx_len; k++){
				memset(backcnt[k], 0, sizeof(int)*(N_ITEMS_DB+1));
				empty[k]=0;
			}

			// Backward scan the first appearance of SDB to prune the search space
			for(j=0;j<L_proj_DB[i].m_nSup;j++){
				bb=0;
				n_prefix=0;
				strt=L_proj_DB[i].projPnts[j].ppSeq[prfx_len-bb];
				if(j>0){
					while((bb<prfx_len)&&(empty[prfx_len-1-bb]==0)){
						while((bb<(prfx_len-1))&&(data[--strt]!=L_proj_DB[i].m_pnPat[prfx_len-2-bb]));
						bb++;
					}
					if(bb>=prfx_len)
						break;
				}


				while(bb<prfx_len){
					l=strt-1;//starting point
					next=0;
					l_prefix=n_prefix;
					if(bb<(prfx_len-1)){
						nd=L_proj_DB[i].projPnts[j].ppSeq[prfx_len-2-bb];//end point
						while(l>nd){
							if(backcnt[prfx_len-1-bb][data[l]]==j){
								backcnt[prfx_len-1-bb][data[l]]++;
								n_prefix++;
							}
							if((next==0)&&(data[l]==L_proj_DB[i].m_pnPat[prfx_len-2-bb])){
								strt=l;
								next=1;
							}
							l--;
						}

						if(next==0)
							strt=nd;
					}
					else{
						while((l>=0)&&(data[l]!=END_SEQ)){
							if(backcnt[prfx_len-1-bb][data[l]]==j){
								backcnt[prfx_len-1-bb][data[l]]++;
								n_prefix++;
							}
							l--;
						}
					}
					bb++;
					if(j==0)
						empty[prfx_len-bb]=n_prefix-l_prefix;
					if(j>0){
						l_prefix=n_prefix-l_prefix;
						if(empty[prfx_len-bb]>l_prefix)
							empty[prfx_len-bb]=l_prefix;
						while((bb<prfx_len)&&(empty[prfx_len-1-bb]==0)){
							while((bb<(prfx_len-1))&&(data[--strt]!=L_proj_DB[i].m_pnPat[prfx_len-2-bb]));
							bb++;
						}
					}
				}

				if(n_prefix==0)
					break;
			}
			//n_prefix==0:closed candidate,
			//otherwise:none closed candidate but needs to be extended to find some closed sequence candidates
			bid(L_proj_DB[i],n_prefix);
		}
		else{//it can be pruned and we should release the memory occupied by L_proj_DB[i]
			;
		}

	}


	//free memory occupied by the projDB
	for (i=0; i<L_freq; i++){
		free(L_proj_DB[i].m_pnPat);
		for(j=0;j<L_proj_DB[i].m_nSup;j++){
			free(L_proj_DB[i].projPnts[j].ppSeq);
		}
		free(L_proj_DB[i].projPnts);
	}
	free(L_proj_DB);

	return;
}

/*********************************************************************
 Module: Read in the database and scane it once to find the frequent
 items, initialize the data structures for frequent 1-sequences
**********************************************************************/
void frequent_1_sequences(void){
	int i;
	int j;
	int i_sdb;
	int itemID;
	int G_freq;
	int CRRNT_seqID;
	FILE *f_data;
	struct PROJ_DB *G_proj_DB;


	//Scan the database on disk once into the memory (SDB), and find the frequent items
	f_data=openfile(F_SDB, "r");
	CRRNT_seqID=0;
	i_sdb=0;
	memset(G_counted, -1, sizeof(int)*(N_ITEMS_DB+1));
	while(CRRNT_seqID<=NUM_SEQ){

		fscanf(f_data, "%i", &itemID);
		if(itemID>=0){
			SDB[i_sdb++]=itemID;
			if(G_counted[itemID]<CRRNT_seqID){
				G_count[itemID]++;
				G_counted[itemID]=CRRNT_seqID;
			}
		}
		else
			if(itemID==-1){
				SEQ_idx[CRRNT_seqID]=i_sdb;
				CRRNT_seqID++;
				SDB[i_sdb++]=END_SEQ;
			}
			else
				if(itemID==-2){
					CRRNT_seqID++;
					SDB[i_sdb++]=END_SDB;
				}
	}
	fclose(f_data);


	// Initialize the header tables for projected databases of frequent items
	G_freq=0;
	for (i=0; i<N_ITEMS_DB; i++){
		if(G_count[i]>=MIN_SUP)
			G_freq++;
	}

	G_proj_DB=(struct PROJ_DB *)makememory(sizeof(struct PROJ_DB)*G_freq);
	for(G_freq=i=0;i<N_ITEMS_DB;i++){
		if(G_count[i]>=MIN_SUP){
			G_proj_DB[G_freq].m_nPatLen=1;
			G_proj_DB[G_freq].Sup_cnt=0;
			G_proj_DB[G_freq].lastID=-1;
			G_proj_DB[G_freq].m_pnPat=(int *)makememory(sizeof(int)*G_proj_DB[G_freq].m_nPatLen);
			G_proj_DB[G_freq].m_pnPat[0]=i;
			G_proj_DB[G_freq].m_nSup=G_count[i];
			G_proj_DB[G_freq].projPnts=(struct PROJ_SEQ*)makememory(sizeof(struct PROJ_SEQ)*G_proj_DB[G_freq].m_nSup);

			for(j=0;j<G_proj_DB[G_freq].m_nSup;j++){
				G_proj_DB[G_freq].projPnts[j].ppSeq=(int *)makememory(sizeof(int)*(G_proj_DB[G_freq].m_nPatLen+1));
				memset(G_proj_DB[G_freq].projPnts[j].ppSeq,0,sizeof(int)*(G_proj_DB[G_freq].m_nPatLen+1));
			}
			G_freq_idx[i]=G_freq;
			G_freq++;
		}
		else
			G_freq_idx[i]=-1;
	}


	// Scan SDB once to build projected database for each frequent item (1st appearance)
	j=0;
	int seqID=0;
	int *data=SDB;
	int dataIDX;
	while(data[j]!=END_SDB){
		if(data[j]<END_SEQ)
			dataIDX=G_freq_idx[data[j]];
		else
			dataIDX=-1;

		if((data[j]>=0)&&(dataIDX>=0)){
			if(G_proj_DB[dataIDX].lastID<seqID){
				G_proj_DB[dataIDX].projPnts[G_proj_DB[dataIDX].Sup_cnt].ppSeq[0]=j;
				G_proj_DB[dataIDX].projPnts[G_proj_DB[dataIDX].Sup_cnt].ppSeq[1]=j;
				G_proj_DB[dataIDX].Sup_cnt++;
				G_proj_DB[dataIDX].lastID=seqID;
			}
			else
				G_proj_DB[dataIDX].projPnts[G_proj_DB[dataIDX].Sup_cnt-1].ppSeq[1]=j;
		}
		else
			if(data[j]==END_SEQ){
				seqID++;
			}
		j++;
	}

	int k,n_prefix;
	for (i=0; i<G_freq; i++){
		memset(backcnt[0], 0, sizeof(int)*(N_ITEMS_DB+1));
		// Backward scan the first appearance of SDB to prune the search space
		for(j=0;j<G_proj_DB[i].m_nSup;j++){
			k=G_proj_DB[i].projPnts[j].ppSeq[0];
			// Backward scan
			int l=k-1;
			n_prefix=0;
			while((l>=0)&&(data[l]!=END_SEQ)){
				if(backcnt[0][data[l]]==j){
					backcnt[0][data[l]]++;
					n_prefix++;
				}
				l--;
			}
			if(n_prefix==0)
				break;
		}

		if(n_prefix==0){//cannot be pruned
			// Check if it's closed by backward scanning of the last appearance of SDB
			memset(backcnt[0], 0, sizeof(int)*(N_ITEMS_DB+1));

			for(j=0;j<G_proj_DB[i].m_nSup;j++){
				k=G_proj_DB[i].projPnts[j].ppSeq[1];
				// Backward scan
				int l=k-1;
				n_prefix=0;
				while((l>=0)&&(data[l]!=END_SEQ)){
					if(backcnt[0][data[l]]==j){
						backcnt[0][data[l]]++;
						n_prefix++;
					}
					l--;
				}
				if(n_prefix==0)
					break;
			}
			//n_prefix==0:closed candidate,
			//otherwise:none closed candidate but needs to be extended to find some closed sequence candidates
			bid(G_proj_DB[i],n_prefix);
		}
		else{//Release the memory occupied by G_proj_DB[i]
			;
		}
	}

	for (i=0; i<G_freq; i++){
		free(G_proj_DB[i].m_pnPat);
		for(j=0;j<G_proj_DB[i].m_nSup;j++){
			free(G_proj_DB[i].projPnts[j].ppSeq);
		}
		free(G_proj_DB[i].projPnts);
	}
	free(G_proj_DB);
	return;
}

/*********************************************************************/


/*********************************************************************
 Main() routine
*********************************************************************/
int main(int argc, char *argv[]){
	int i;
	//struct _timeb t_start, t_end;

	if ((argv[1]==NULL) || (argv[2]==NULL) ) {// argv[3]==gap threshold may also need to be provided
		printf("Usage: Please give two arguments.\n");
		printf("       The first argument is the name of the .spec file.\n");
		printf("       The second argument is the relative support threshold.\n");
		printf("Details please refer to the attached document.\n");
		exit(-1);
	}
	SEQspec=argv[1];
	f_MIN_SUP=atof(argv[2]);

	init();
	n_fcs=(int *)makememory(sizeof(int)*(MAX_LEN_SEQ+1));
	memset(n_fcs, 0, sizeof(int)*(MAX_LEN_SEQ+1));
	G_count=(int *)makememory(sizeof(int)*(N_ITEMS_DB+1));
	memset(G_count, 0, sizeof(int)*(N_ITEMS_DB+1));
	G_counted=(int *)makememory(sizeof(int)*(N_ITEMS_DB+1));
	memset(G_counted, -1, sizeof(int)*(N_ITEMS_DB+1));
	G_freq_idx=(int *)makememory(sizeof(int)*(N_ITEMS_DB+1));
	memset(G_freq_idx, -1, sizeof(int)*(N_ITEMS_DB+1));
	SDB=(int *)makememory(sizeof(int)*(AVG_LEN_SEQ+3)*NUM_SEQ);
	memset(SDB, (N_ITEMS_DB+2), sizeof(int)*(AVG_LEN_SEQ+3)*NUM_SEQ);
	SEQ_idx=(int *)makememory(sizeof(int)*(NUM_SEQ+1));
	memset(SEQ_idx, 0, sizeof(int)*(NUM_SEQ+1));
	for(i=0; i<NBACK; i++){
		backcnt[i]=(int*)makememory(sizeof(int)*(N_ITEMS_DB+1));
		memset(backcnt[i], 0, sizeof(int)*(N_ITEMS_DB+1));
	}

    //	_ftime(&t_start);
	f_output=openfile("frequent.dat", "w+");

	//Scane database once to find the frequent 1-sequences
	frequent_1_sequences();


	fclose(f_output);
    //	_ftime(&t_end);
    //	print_result(double(t_end.time-t_start.time)+(t_end.millitm-t_start.millitm)/1000.0);

	for(i=0; i<NBACK; i++){
		free(backcnt[i]);
	}
	free(G_freq_idx);
	free(SEQ_idx);
	free(SDB);
	free(G_count);
	free(n_fcs);
	return 0;
}
