const app = Vue.

    createApp({
        data() {
            return {
                accounts: [],
                clients: [],
                accountOrigin: "",
                accountDestiny: "",
                accountDestiny2: "",
                amount: "",
                description: "",
            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a, b)=> {
                        return a.id - b.id
                    });
                })
                .catch(function (error) {
                    console.log(error);
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
                    .catch(function (error) {
                        console.log(error);
                    });
            },

            newTransfer() {
                axios.post("/api/clients/current/transactions",`accountOrigin=${this.accountOrigin}&accountDestiny=${this.accountDestiny}&amount=${this.amount}&description=${this.description}`,
                {headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal("Good job!", "Successful transfer!", "success", {
                        timer: 4000,
                    })
                })
                .catch(error => {
                    swal("Error!", error.response.data, "error", {
                        timer: 4000,
                    })

                });

            },
        }

    }).mount('#app')