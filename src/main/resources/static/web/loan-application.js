const app = Vue.

    createApp({
        data() {
            return {
                accounts: [],
                clients: [],
                loans: [],
                payments: [],
                applyLoan: [],
                mortgage: [],
                personal: [],
                automotive: [],
                loanId: "",
                loanName: "",
                amount: "",
                selectAccount: "",
                payment: "",
            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a, b) => {
                        return a.id - b.id
                    });
                    this.applyLoan = this.clients.loans;
                })
                .catch(function (error) {
                    console.log(error);
                });

            axios.get('/api/loans')
                .then((response) => {
                    this.loans = response.data;
                });

        },

        methods: {
            newDate(creationDate) {
                creationDate = new Date(creationDate).toLocaleString();

                return creationDate;
            },
            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
            },
            logOut() {
                axios.post('/api/logout')
                    .then(response => location.href = '/web/index.html')
            },
            newLoan() {
                axios.post("/api/clients/current/loans", {
                    "id": `${this.loanId}`,
                    "amount": `${this.amount}`,
                    "payments": `${this.payments}`,
                    "destinyAccount": `${this.selectAccount}`,
                })
                    .then(response => {
                        swal("Loan granted!", "You can see it in the loan section within accounts", "success", {
                            timer: 4000,
                        });

                    })
                    .catch(error => {
                        swal(error.response.data, "Error", "error", {
                            timer: 4000,
                        })

                    });
            },
        },

    }).mount('#app')