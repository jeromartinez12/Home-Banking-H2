const urlParams = new URLSearchParams(window.location.search);
const urlName = urlParams.get('id');

const app = Vue.
    createApp({
        data() {
            return {
                clients: [],
                accounts: [],
                idAccounts: [],
                transactions: [],

                clientAccount: '',
                fromDate: '',
                toDate: '',

            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a, b) => {
                        return a.id - b.id
                    });
                    this.idAccounts = this.accounts.find(account => account.id == urlName);

                    this.transactions = this.idAccounts.transaction.sort((a, b) => {
                        return new Date(b.date) - new Date(a.date)
                    });
                    this.clientAccount = this.idAccounts.number;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {
            newDate(date) {
                creationDate = new Date(date).toLocaleString();

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
            newPdf() {
                axios.post("/api/transactions/filtered",
                    {
                        "fromDate": this.fromDate,
                        "toDate": this.toDate,
                        "clientAccount": this.clientAccount

                    })
                    .then(response => {
                        swal("Generated PDF!", "The PDF has been downloaded, check the desktop", "success", {
                            timer: 4000,
                        })
                    .then(response => window.location.reload())
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    })

            },
        }

    }).mount('#app')